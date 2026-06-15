# Sistema de Transporte - Microservicios UPeU

## Descripción
Sistema de gestión de reservas de transporte basado en arquitectura de microservicios con Spring Boot y Spring Cloud.

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
| ms-reserva | 8081 | Gestión de reservas |

## Requisitos previos
- Java 21
- Docker Desktop
- Maven

## Instalación y ejecución

### Opción A: Ejecución local (sin Docker)

#### 1. Clonar el repositorio
```bash
git clone https://github.com/Lucky-pixel-web/Examen3-Transporte.git
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
5. ms-reserva (puerto 8081)

#### 4. Verificar en Eureka
http://localhost:8761

### Opción B: Ejecución con Docker Compose
```bash
docker-compose up --build
```
> Los servicios arrancan en orden automáticamente gracias a los healthchecks.

## Documentación API (Swagger)
- Reservas: http://localhost:8081/swagger-ui/index.html

## Autenticación

### Registrar usuario ADMIN

POST http://localhost:8080/auth/register?rol=ADMIN

{
"username": "admin",
"password": "123456"
}

### Login como ADMIN

POST http://localhost:8080/auth/login/admin

{
"username": "admin",
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
| POST | ✅ | ❌ |
| PUT | ✅ | ❌ |
| DELETE | ✅ | ❌ |

## Endpoints principales

### Reservas (requiere Bearer Token)
- `GET /api/reservas` — Listar todas
- `GET /api/reservas/{id}` — Buscar por ID
- `POST /api/reservas` — Crear
- `PUT /api/reservas/{id}` — Actualizar
- `DELETE /api/reservas/{id}` — Eliminar (lógico)

## Notas importantes
- El eliminado es **lógico** (campo estado = INACTIVO), no físico
- El auth-service bloquea la cuenta **5 minutos** tras 3 intentos fallidos
- Todas las bases de datos se crean automáticamente si no existen