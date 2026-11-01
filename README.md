# Spring Boot CQRS Event Sourcing Demo

[![Java CI with Maven](https://github.com/hendisantika/springboot-cqrs-event-sourcing-demo/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/springboot-cqrs-event-sourcing-demo/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.x-6DB33F?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-336791?logo=postgresql)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)

A demonstration of **CQRS** (Command Query Responsibility Segregation) and **Event Sourcing** patterns using Spring Boot,
organised with a **Hexagonal (Ports & Adapters)** architecture.

## Technology Stack

| Area          | Technology                                                    |
|---------------|---------------------------------------------------------------|
| Language      | Java 25 (LTS)                                                 |
| Framework     | Spring Boot 4.1.x (Spring Framework 7, Jakarta EE 11)         |
| Persistence   | Spring Data JPA / Hibernate 7                                 |
| Database      | PostgreSQL 18 (runtime), H2 in-memory (tests)                 |
| Serialization | Jackson 3 (`tools.jackson`) for event payloads                |
| Mapping       | MapStruct 1.6 + Lombok (with `lombok-mapstruct-binding`)      |
| Validation    | Jakarta Bean Validation                                       |
| Build         | Maven (via Maven Wrapper)                                     |
| Containers    | Docker & Docker Compose (Spring Boot Docker Compose support)   |
| CI / Updates  | GitHub Actions + Dependabot                                   |

## Prerequisites

- JDK 25 or higher (e.g. Temurin / Corretto 25)
- Docker Desktop (for PostgreSQL)
- No local Maven needed — use the bundled Maven Wrapper (`./mvnw`)

## Architecture

```
            ┌──────────────── Command side ────────────────┐
HTTP POST/PUT ─► OfferController ─► OfferServiceRest ─► CommandService
                                                           │  (Offer aggregate)
                                                           ▼
                                   OfferStore ─► EventStream / EventDescriptor   (event store, JSON events)
                                                           │
                                                           ▼  DomainEventPublisher (Spring events)
            ┌──────────────── Query side ──────────────────┐
HTTP GET ─────► OfferController ─► OfferQueryService ◄── OfferEventListener ─► offer (read model table)
```

- **Command side** (`offer.domain`): commands (`CreateOfferCommand`, `UpdateOfferCommand`, `MarkOfferAsPaidCommand`)
  are handled by the `Offer` aggregate, which emits domain events (`OfferCreatedEvent`, `OfferUpdatedEvent`,
  `OfferPaidEvent`).
- **Event Sourcing** (`infrastructure.db`): events are serialized to JSON and appended to an `EventStream`
  per aggregate (optimistic locking via `@Version`). The aggregate state is rebuilt by replaying its events.
- **Query side** (`offer.query`): `OfferEventListener` projects events into a denormalised `offer` read-model table,
  which is served by `OfferQueryService`.
- **Ports & Adapters**: `port.primary` / `port.secondary` interfaces with REST and DB adapters in `adapter`.

```
src/main/java/com/hendisantika
├── infrastructure          # generic building blocks: commands, events, event store entities, UUIDs
└── offer
    ├── domain              # write model: aggregate, commands, events, ports, adapters (REST, DB)
    └── query               # read model: DTOs, query service, projection (event listener + JPA)
```

## Database Setup

This project uses PostgreSQL 18 running in Docker (`docker-compose.yml`):

| Setting  | Value    |
|----------|----------|
| Database | `offer`  |
| Username | `yu71`   |
| Password | `53cret` |
| Port     | `5432`   |

## Running the Application

### 1. Start PostgreSQL

Spring Boot Docker Compose support starts the PostgreSQL container automatically when you run the application.
Alternatively, start it manually:

```bash
docker compose up -d
docker ps | grep springboot-cqrs-postgres
```

### 2. Build (compile + test)

```bash
./mvnw clean package
```

### 3. Run

```bash
./mvnw spring-boot:run
```

The application starts on port **8080**. Health check: `GET http://localhost:8080/actuator/health`.

## API Endpoints

| Method | Path                       | Description                          | Success              |
|--------|----------------------------|--------------------------------------|----------------------|
| `POST` | `/api/offers`              | Create an offer                      | `201` + `Location`   |
| `PUT`  | `/api/offers/{offerUUID}`  | Update product name / amount         | `200`                |
| `GET`  | `/api/offers/{offerUUID}`  | Read the offer from the read model   | `200` / `404`        |

Invalid payloads (e.g. empty `productName` or missing `amount`) return `400 Bad Request`.

### Examples

```bash
# Create an offer (the new resource URL is returned in the Location header)
curl -i -X POST http://localhost:8080/api/offers \
  -H 'Content-Type: application/json' \
  -d '{"productName":"Laptop","amount":1500}'

# Update it
curl -X PUT http://localhost:8080/api/offers/<offerUUID> \
  -H 'Content-Type: application/json' \
  -d '{"productName":"Laptop Pro","amount":2000}'

# Query the read model
curl http://localhost:8080/api/offers/<offerUUID>
```

Sample response:

```json
{
  "uuid": "60a9f224-2972-46b0-954f-1ab2ca241ba3",
  "productName": "Laptop Pro",
  "amount": 2000.00,
  "status": "NEW",
  "createdAt": "2026-11-02T06:17:05.241149",
  "createdBy": "rest-api-user",
  "updatedAt": "2026-11-02T06:17:05.44856",
  "updatedBy": "rest-api-user",
  "paidAt": null,
  "paidBy": null,
  "version": 1
}
```

## Configuration

- Runtime configuration: `src/main/resources/application.properties` (PostgreSQL).
  To use H2 instead, comment out the PostgreSQL block and uncomment the H2 block.
- Test configuration: `src/test/resources/application.properties` uses an in-memory H2 database and disables
  Docker Compose, so tests (and CI) run without PostgreSQL or Docker.

## Testing

```bash
./mvnw test
```

## Continuous Integration

GitHub Actions workflow [`.github/workflows/maven.yml`](.github/workflows/maven.yml) runs on every push and pull
request to `main`:

1. Sets up **JDK 25** (Temurin) with Maven cache
2. Builds and tests with `./mvnw -B package`
3. On pushes to `main`, submits the Maven dependency graph to improve Dependabot alerts

## Dependency Updates (Dependabot)

[`.github/dependabot.yml`](.github/dependabot.yml) keeps dependencies fresh (schedule: 21:00 Asia/Jakarta):

| Ecosystem        | Frequency | Grouping / rules                                                                           |
|------------------|-----------|--------------------------------------------------------------------------------------------|
| Maven            | daily     | groups: `spring-boot`, `annotation-processors`, `maven-build`, `maven-minor-and-patch`; Spring Boot major bumps ignored (need manual migration) |
| Docker Compose   | weekly    | PostgreSQL image; major versions ignored (need data migration)                             |
| GitHub Actions   | daily     | all actions grouped into one PR                                                            |

PRs are labelled (`dependencies`, `java`, `docker`, `github-actions`) and use conventional commit prefixes
(`build(deps)`, `build(deps-dev)`, `ci(deps)`).

## Docker Commands

```bash
docker compose up -d                                              # start the database
docker compose down                                               # stop the database
docker compose down -v                                            # stop and remove volumes (clean slate)
docker logs springboot-cqrs-postgres                              # view PostgreSQL logs
docker exec -it springboot-cqrs-postgres psql -U yu71 -d offer    # PostgreSQL CLI
```

## Author

**Hendi Santika** — [s.id/hendisantika](https://s.id/hendisantika) · Telegram [@hendisantika34](https://t.me/hendisantika34)
