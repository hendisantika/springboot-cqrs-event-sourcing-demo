# Spring Boot CQRS Event Sourcing Demo

A demonstration of CQRS (Command Query Responsibility Segregation) and Event Sourcing patterns using Spring Boot.

## Technology Stack

- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- PostgreSQL 18
- Docker & Docker Compose
- MapStruct
- Lombok
- Maven

## Prerequisites

- Java 21 or higher
- Docker Desktop
- Maven 3.8+

## Database Setup

This project uses PostgreSQL 18 running in Docker. The database configuration is:

- **Database**: offer
- **Username**: yu71
- **Password**: 53cret
- **Port**: 5432

## Running the Application

### 1. Start PostgreSQL Database

The application uses Spring Boot Docker Compose support, which will automatically start the PostgreSQL container when
you run the application. Alternatively, you can start it manually:

```bash
docker-compose up -d
```

To verify the PostgreSQL container is running:

```bash
docker ps | grep springboot-cqrs-postgres
```

### 2. Build the Application

```bash
./mvnw clean package
```

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

The application will start on port 8080.

## Docker Commands

### Start the database

```bash
docker-compose up -d
```

### Stop the database

```bash
docker-compose down
```

### Stop and remove volumes (clean slate)

```bash
docker-compose down -v
```

### View PostgreSQL logs

```bash
docker logs springboot-cqrs-postgres
```

### Access PostgreSQL CLI

```bash
docker exec -it springboot-cqrs-postgres psql -U yu71 -d offer
```

## Configuration

The application properties are configured in `src/main/resources/application.properties`.

To switch back to H2 in-memory database, comment out the PostgreSQL configuration and uncomment the H2 configuration in
the application.properties file.

## API Endpoints

The application exposes REST endpoints for managing offers (to be documented based on your implementation).

## Architecture

This project demonstrates:

- **CQRS Pattern**: Separation of command and query responsibilities
- **Event Sourcing**: Storing state changes as a sequence of events
- **Domain-Driven Design**: Clear separation of domain logic
