# Airline Microservices

A modern Spring Boot 3 microservices sample project for an airline domain, built as a Maven multi-module application.

This repository demonstrates a practical architecture for splitting business functionality into independently runnable services while sharing common code through a dedicated library module.

## ✨ Features

- Modular monorepo structure with multiple Spring Boot services
- Shared reusable components through the common library
- Database-backed services using Spring Data JPA
- REST-based service design
- Secure authentication flow in the user service
- Easy local development with Maven and Maven Wrapper

## 🧩 Project Structure

```text
airline-microservices/
├── common-lib/                 # Shared library used by services
├── services/                   # Aggregator module for all services
│   ├── airline-core-service/   # Core airline business service
│   ├── flight-ops-service/     # Flight operations domain service
│   ├── location-service/       # Location-related service
│   └── user-service/           # User and authentication service
├── pom.xml                     # Root Maven configuration
└── services/pom.xml            # Services aggregator pom
```

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.5.4
- Spring Cloud 2025.0.0
- Maven
- MySQL
- Spring Data JPA
- Lombok
- Spring Security

## 🚀 Getting Started

### Prerequisites

Make sure the following are installed on your machine:

- JDK 17+
- Maven 3.8+
- MySQL running locally

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd airline-microservices
```

### 2. Create the database

```sql
CREATE DATABASE airline_location_db;
```

If your local MySQL credentials differ, update the datasource settings in each service's application configuration.

### 3. Build the project

```bash
mvn clean install
```

### 4. Run a service

Run each service from its own directory:

```bash
cd services/location-service
mvn spring-boot:run
```

```bash
cd services/user-service
mvn spring-boot:run
```

```bash
cd services/airline-core-service
mvn spring-boot:run
```

```bash
cd services/flight-ops-service
mvn spring-boot:run
```

## 🌐 Local Ports

The services are configured to run on these local ports:

- Location Service: 8080
- User Service: 8081
- Airline Core Service: 8082
- Flight Operations Service: 8083

## 📦 Maven Wrapper

Each service also includes a Maven wrapper for convenience:

```bash
cd services/user-service
./mvnw spring-boot:run
```

## 📝 Notes

- The services currently rely on local configuration files under each service's resources directory.
- Database settings are environment-specific and may need adjustment depending on your MySQL setup.
- The shared library module provides reusable components for the services.

## 📄 License

This project does not currently include a license file. If you plan to share or distribute it publicly, consider adding an appropriate open-source license.
