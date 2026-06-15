# Sistema de Gestión - Microservicios UPeU

## Descripción
Sistema de gestión de instructores, alumnos y talleres basado en
arquitectura de microservicios con Spring Boot y Spring Cloud.

## Tecnologías
- Java 21
- Spring Boot 3.5.4
- Spring Cloud 2025.0.0
- MySQL 8.0
- Docker
- Maven
- Swagger/OpenAPI

## Microservicios

| Servicio | Puerto | Descripción |
|---|---|---|
| service-registry | 8761 | Eureka Server |
| config-server | 8088 | Servidor de configuración |
| api-gateway | 8080 | Puerta de enlace + JWT |
| auth-service | 8090 | Autenticación JWT |
| ms-gestion-instructor | 8081 | Gestión de instructores |
| ms-gestion-alumno | 8082 | Gestión de alumnos |
| ms-gestion-taller | 8083 | Gestión de talleres (consume instructor y alumno vía OpenFeign) |

## Requisitos previos
- Java 21
- Docker Desktop
- Maven

## Instalación y ejecución

### Opción A: Ejecución local (sin Docker)

#### 1. Clonar el repositorio
```bash
git clone <url-del-repositorio>
```

#### 2. Levantar MySQL con Docker
```bash
docker run --name mysql-local -e MYSQL_ROOT_PASSWORD=mysql123 -p 3307:3306 -d mysql:8.0
```

#### 3. Arrancar los servicios en orden
1. service-registry (puerto 8761)
2. config-server (puerto 8088)
3. auth-service (puerto 8090)
4. api-gateway (puerto 8080)
5. ms-gestion-instructor (puerto 8081)
6. ms-gestion-alumno (puerto 8082)
7. ms-gestion-taller (puerto 8083)

#### 4. Verificar en Eureka

http://localhost:8761

### Opción B: Ejecución con Docker Compose

```bash
docker-compose up --build
```

> Los servicios arrancan en orden automáticamente gracias a los healthchecks.

## Documentación API (Swagger)
- Instructor: http://localhost:8081/swagger-ui/index.html
- Alumno: http://localhost:8082/swagger-ui/index.html
- Taller: http://localhost:8083/swagger-ui/index.html

## Autenticación

### Registrar usuario ADMIN

POST http://localhost:8080/auth/register?rol=ADMIN
{
"username": "admin8080",
"password": "123456"
}

### Login como ADMIN

POST http://localhost:8080/auth/login/admin
{
"username": "admin8080",
"password": "123456"
}

### Login como USER

POST http://localhost:8080/auth/login/user
{
"username": "user1",
"password": "123456"
}

> Usar el token retornado como **Bearer Token** en cada request.

## Permisos por rol

| Operación | ADMIN | USER |
|---|---|---|
| GET /api/** (lista) | ✅ | ✅ |
| GET /api/**/{id} | ✅ | ❌ |
| GET /api/**/buscar | ✅ | ❌ |
| POST | ✅ | ❌ |
| PUT | ✅ | ❌ |
| DELETE | ✅ | ❌ |

## Endpoints principales

### Instructores (requiere Bearer Token)
- `GET /api/instructores` — Listar todos
- `GET /api/instructores/{id}` — Buscar por ID
- `GET /api/instructores/buscar?nombre=X` — Buscar por nombre
- `POST /api/instructores` — Crear
- `PUT /api/instructores/{id}` — Actualizar
- `DELETE /api/instructores/{id}` — Eliminar (lógico)

### Alumnos (requiere Bearer Token)
- `GET /api/alumnos` — Listar todos
- `GET /api/alumnos/{id}` — Buscar por ID
- `GET /api/alumnos/buscar?nombre=X` — Buscar por nombre
- `POST /api/alumnos` — Crear
- `PUT /api/alumnos/{id}` — Actualizar
- `DELETE /api/alumnos/{id}` — Eliminar (lógico)

### Talleres (requiere Bearer Token)
- `GET /api/talleres` — Listar todos (incluye nombre instructor y alumno)
- `GET /api/talleres/{id}` — Buscar por ID
- `GET /api/talleres/buscar?nombre=X` — Buscar por nombre
- `POST /api/talleres` — Crear
- `PUT /api/talleres/{id}` — Actualizar
- `DELETE /api/talleres/{id}` — Eliminar (lógico)

## Notas importantes
- El eliminado es **lógico** (campo estado = INACTIVO), no físico
- El auth-service bloquea la cuenta **5 minutos** tras 3 intentos fallidos
- ms-gestion-taller consume ms-gestion-instructor y ms-gestion-alumno via **OpenFeign**
- Todas las bases de datos se crean automáticamente si no existen