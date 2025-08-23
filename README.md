## Tasks Management API (Spring Boot)

A simple task management REST API built with Spring Boot 3, secured with HTTP Basic, and backed by PostgreSQL. It exposes CRUD endpoints for tasks and basic user management for bootstrapping authentication.

### Tech stack
- **Java**: 21
- **Framework**: Spring Boot 3.5.x (Web, Validation)
- **Security**: Spring Security (HTTP Basic, method-level authorization)
- **Persistence**: Spring Data JPA, PostgreSQL
- **Mapping**: MapStruct, Lombok
- **Docs**: springdoc-openapi (Swagger UI)
- **Build**: Maven (mvnw wrapper)

### Requirements
- JDK 21+
- PostgreSQL 13+
- Bash-compatible shell (for `./mvnw`)

### Getting started
1. Clone the repository
```bash
git clone <your-repo-url>
cd <repo-dir>
```

2. Create a PostgreSQL database (example)
```bash
psql -U postgres -h localhost -p 5432 -c "CREATE DATABASE \"tasksManagement\";"
```

3. Configure application properties
- Default config is in `src/main/resources/application.properties`. Override via environment variables in local/dev:
  - `SPRING_DATASOURCE_URL` (e.g., `jdbc:postgresql://localhost:5432/tasksManagement`)
  - `SPRING_DATASOURCE_USERNAME`
  - `SPRING_DATASOURCE_PASSWORD`
  - `SPRING_JPA_HIBERNATE_DDL_AUTO` (default `update`)

Example (Linux/macOS):
```bash
export SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/tasksManagement"
export SPRING_DATASOURCE_USERNAME="postgres"
one example only
export SPRING_DATASOURCE_PASSWORD="<your-password>"
```

### Run the application
- Using Spring Boot plugin (recommended for dev):
```bash
./mvnw spring-boot:run
```
- Or build a jar and run:
```bash
./mvnw clean package
java -jar target/tasks_management-0.0.1-SNAPSHOT.jar
```

The API will start at `http://localhost:8080`.

### Authentication and roles
- HTTP Basic is enabled. Endpoints are protected via `@PreAuthorize` with roles:
  - `ROLE_ADMIN`
  - `ROLE_USER`
- Create initial users via a temporary endpoint:
```bash
# Create admin
curl -X POST "http://localhost:8080/api/users/create?username=admin&password=adminpass&role=ROLE_ADMIN"

# Create standard user
curl -X POST "http://localhost:8080/api/users/create?username=testuser&password=testpass&role=ROLE_USER"
```
- Fetch current authenticated user and role:
```bash
curl -u admin:adminpass http://localhost:8080/api/users/me
```

### API overview
- **Base URL**: `http://localhost:8080`

#### Tasks
- **GET** `/api/tasks` — list all tasks (roles: ADMIN, USER)
- **GET** `/api/tasks/{id}` — get task by id (roles: ADMIN, USER)
- **POST** `/api/tasks` — create task (roles: ADMIN, USER)
  - Body:
    ```json
    {
      "name": "Task name",
      "description": "Task description",
      "deadline": "2025-12-31"
    }
    ```
- **PUT** `/api/tasks/{id}` — update task (role: ADMIN)
- **PUT** `/api/tasks/{id}/status/{status}` — update task status (roles: ADMIN, USER)
  - `status` values: `PENDING`, `IN_COURSE`, `COMPLETED`
- **DELETE** `/api/tasks/{id}` — delete task (role: ADMIN)

#### Users
- **POST** `/api/users/create?username={u}&password={p}&role={ROLE_USER|ROLE_ADMIN}` — create user (temporary helper)
- **GET** `/api/users/me` — info of the authenticated user

### Error responses
A global exception handler returns structured errors, for example:
```json
{
  "timestamp": "2024-01-01T12:34:56.789",
  "status": 400,
  "error": "Validation Error",
  "errors": {
    "name": "El nombre de la tarea es obligatorio"
  }
}
```
For not found:
```json
{
  "timestamp": "2024-01-01T12:34:56.789",
  "status": 404,
  "error": "Not Found",
  "message": "Task with id 999 not found"
}
```

### API docs (Swagger)
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

### CORS
- `TaskController`: `@CrossOrigin(origins = "*")`
- `UserController`: `@CrossOrigin(origins = "http://localhost:4200")`

### Project structure
```
src/
  main/
    java/com/tasks/management/tasks_management/
      config/
      controller/
      exceptions/
      mapper/
      model/
        dto/request/
        dto/response/
        vo/
      repository/
      service/
        impl/
      TasksManagementApplication.java
    resources/
      application.properties
  test/
rest/
  tasks.http
  users.http
```

### REST client examples
- See `rest/tasks.http` and `rest/users.http` for ready-to-run examples (VS Code REST Client / IntelliJ HTTP Client).

### Useful Maven commands
```bash
# Run with dev reload support (if devtools is present)
./mvnw spring-boot:run

# Build
./mvnw clean package

# Run tests
./mvnw test
```

### Notes
- Replace any sample credentials and database settings with your own.
- For production, configure CORS properly and remove the temporary user-creation endpoint.

### License
Add your preferred license here.