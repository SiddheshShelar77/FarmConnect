# FarmConnect – Livestock Management & QR-Based Identification System

FarmConnect is a livestock management backend application built using Java and Spring Boot. It helps manage farmer information, animal registrations, vaccination schedules, and health records. It also provides QR-code-based animal identification, allowing users to retrieve an animal's profile using its unique tag number.

## Features

- **Farmer Management:** Create, retrieve, update, and delete farmer records.
- **Animal Management:** Register and manage livestock records.
- **Health Record Management:** Maintain animal health and medical records.
- **Vaccination Management:** Store vaccination details and track upcoming vaccinations.
- **QR Code Generation:** Generate unique QR codes for registered animals.
- **Animal Identification:** Identify animals using their unique tag numbers.
- **Public Animal Profile:** Retrieve animal details, vaccination history, and health records through a tag number.

## Technology Stack

| Technology        | Purpose                     |
| ----------------- | --------------------------- |
| Java 21           | Backend programming         |
| Spring Boot 3.5.3 | Backend framework           |
| Spring Data JPA   | Database operations         |
| Hibernate         | ORM                         |
| MySQL             | Relational database         |
| Maven             | Dependency management       |
| ZXing             | QR code generation          |
| REST APIs         | Client-server communication |

## Project Architecture

The application follows a layered architecture:

- **Controller Layer:** Handles incoming HTTP requests.
- **Service Layer:** Contains business logic.
- **Repository Layer:** Performs database operations using Spring Data JPA.
- **Entity Layer:** Defines database entities.
- **DTO Layer:** Transfers structured data between application layers.

## API Endpoints

### Farmer APIs

- `POST /api/farmers` – Create a farmer
- `GET /api/farmers` – Retrieve all farmers
- `GET /api/farmers/{id}` – Retrieve a farmer
- `PUT /api/farmers/{id}` – Update a farmer
- `DELETE /api/farmers/{id}` – Delete a farmer

### Animal APIs

- `POST /api/animals?farmerId={id}` – Register an animal
- `GET /api/animals` – Retrieve all animals
- `GET /api/animals/{id}` – Retrieve an animal
- `GET /api/animals/identify/{tagNumber}` – Identify an animal by tag number
- `GET /api/animals/public/{tagNumber}` – Retrieve the public animal profile
- `GET /api/animals/qr/{tagNumber}` – Generate an animal QR code

### Additional APIs

Health record and vaccination endpoints are included for managing animal medical information and vaccination schedules.

## Example Animal Record

```json
{
  "tagNumber": "FC-COW-001",
  "name": "Gauri",
  "species": "COW",
  "breed": "Gir",
  "gender": "FEMALE",
  "dateOfBirth": "2023-04-15"
}
```

## Setup and Installation

### Prerequisites

- Java 21
- Maven
- MySQL Server
- Git

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/FarmConnect.git
cd FarmConnect
```

### 2. Configure the database

Create a MySQL database named `farmconnect`.

Configure your database credentials in `src/main/resources/application.properties` using environment variables:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/farmconnect?createDatabaseIfNotExist=true
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
server.port=8081
```

Set the `DB_USERNAME` and `DB_PASSWORD` environment variables before running the application.

### 3. Run the application

Using Maven:

```bash
mvn spring-boot:run
```

The backend server will start at:

`http://localhost:8081`

## Future Enhancements

- React-based frontend dashboard
- QR code scanning through a mobile camera
- Farmer authentication and authorization
- Role-based access control
- Cloud deployment
- Improved public profile privacy controls

## Author

Developed as a Java Spring Boot backend project to explore REST API development, database management, and QR-based livestock identification.
