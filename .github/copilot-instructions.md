# Copilot Instructions for tasks_management

## Architecture Overview

- The project is organized under `src/`, with clear separation of concerns:
  - `model/` contains domain models and DTOs (`dto/request`, `dto/response`).
  - `service/` implements business logic.
  - `controller/` exposes API endpoints.
- Data flows from controllers → services → models/DTOs.
- DTOs are split into request/response folders for clarity and type safety.

## Developer Workflows

- **Build:** Use `npm run build` (TypeScript) or `mvn package` (Java) depending on the stack.
- **Test:** Run `npm test` or `mvn test` for automated tests.
- **Debug:** Debug entry points are typically in `src/index.ts` or `src/main/java/.../Application.java`.
- **Start:** Use `npm start` or `mvn spring-boot:run` for local development.

## Project-Specific Conventions

- DTOs are always placed in `src/model/dto/request` and `src/model/dto/response`.
- Service classes are named with the `Service` suffix (e.g., `TaskService`).
- Controllers are named with the `Controller` suffix and reside in `src/controller/`.
- Use records for Java DTOs (see `PersonRecordExample.java`).
- TypeScript interfaces are used for DTOs (see `TaskRequestDto.ts`).

## Integration Points

- External dependencies are managed via `package.json` (Node) or `pom.xml` (Java).
- Database configuration is found in `src/config/` or `src/main/resources/application.properties`.
- API routes are defined in controller files.

## Patterns and Examples

- **DTO Example:**  
  `src/model/dto/request/TaskRequestDto.ts`  
  `src/model/dto/response/TaskResponseDto.ts`
- **Java Record Example:**  
  `PersonRecordExample.java`
- **Service Pattern:**  
  `src/service/TaskService.ts` or `TaskService.java`
- **Controller Pattern:**  
  `src/controller/TaskController.ts` or `TaskController.java`

## Cross-Component Communication

- Controllers call services, which interact with models/DTOs.
- Data validation and transformation are handled in services.
- Responses are always shaped using response DTOs.

---

If any section is unclear or missing details, please specify which workflows, conventions, or integration points need further documentation.
