# Hospital Management System - Spring Boot REST API

A REST API for managing hospitals, branches, patients, visits, doctor notes and medicine orders. Built with Spring Boot, Spring Data JPA (Hibernate) and MySQL.

## Tech Stack
- Java 21, Spring Boot 4
- Spring Web (REST controllers), Spring Data JPA, Hibernate
- MySQL 8
- Bean Validation, global exception handling
- Maven

## Features
- CRUD operations for 9 tables with proper relationships
- Layered architecture: controller, service, repository, entity
- Input validation with clear error messages (for example, a hospital name is required)
- Global exception handler returning clean JSON errors (400, 404)
- Search endpoints (patients by gender, age or phone; orders by doctor or date; users by name or role)
- User passwords are accepted when saving but never returned in responses
- Swagger UI (springdoc-openapi) for interactive API documentation and testing
- Simple web front end (HTML, CSS, JavaScript) to add, edit and delete hospitals at `http://localhost:8080`
## Database Design
Hospital -> Branch -> Address (one to one)
Branch -> Encounter <- Person (a visit links one patient to one branch)
Encounter -> Observation (doctor notes)
Encounter -> MedOrder -> Item (medicine orders and their items)
User (stand-alone table)

Tables are created automatically by Hibernate when the app starts.

## How to Run
1. Install JDK 21 or newer and MySQL 8.
2. Clone the project:
   `git clone https://github.com/RAJESWARY-A/HospitalManagement-SpringBoot.git`
3. Create the file `src/main/resources/application-local.properties` with your MySQL password:
   `spring.datasource.password=YOUR_MYSQL_PASSWORD`
   (If your MySQL user is not `root`, also add `spring.datasource.username=YOUR_USER`.)
4. Run `HospitalappApplication` from your IDE, or run `mvnw spring-boot:run`.
5. The API starts at `http://localhost:8080`. The database `hospitaldb` is created automatically.
6. Open `http://localhost:8080/swagger-ui/index.html` to try every endpoint in the browser.

## API Endpoints

| Resource | Endpoints |
|---|---|
| Hospital | `POST, GET /hospitals` and `GET, PUT, DELETE /hospitals/{id}` |
| Branch | `POST, GET /hospitals/{hid}/branches`, `GET /branches`, `GET, PUT, DELETE /branches/{id}` |
| Address | `GET /addresses`, `GET, PUT /addresses/{id}` |
| Person | `POST, GET /persons`, `GET, PUT, DELETE /persons/{id}`, `GET /persons/gender/{gender}`, `/persons/age/{age}`, `/persons/phone/{phone}` |
| Encounter | `POST /encounters?branchId=&personId=`, `GET /encounters`, `GET, PUT, DELETE /encounters/{id}` |
| Observation | `POST, GET /encounters/{eid}/observations`, `GET, PUT, DELETE /observations/{id}` |
| Medicine order | `POST, GET /encounters/{eid}/medorders`, `GET, PUT, DELETE /medorders/{id}` |
| Item | `POST, GET /medorders/{mid}/items`, `GET, PUT, DELETE /items/{id}` |
| User | `POST, GET /users`, `GET, PUT, DELETE /users/{id}` |

### Example request
```
curl -X POST http://localhost:8080/hospitals -H "Content-Type: application/json" -d "{\"name\":\"PK Das\",\"website\":\"www.pkdas.com\"}"
```

### Example error response
```
{"status":400,"message":"Validation failed","errors":{"name":"Hospital name is required"}}
```

## Planned Improvements
- More front end screens (branches, patients, visits)
- JDBC based reports
- Unit tests with JUnit and Mockito
- Login with Spring Security

## Author
Rajeswari A
[LinkedIn](https://www.linkedin.com/in/rajeswari-a-5430a9285/) | [GitHub](https://github.com/RAJESWARY-A) | [LeetCode](https://leetcode.com/u/Rajeswari-A/)