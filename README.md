# AddressBook Spring Boot Application

## Project Overview

This project demonstrates development of a **Spring Boot Backend Application** for an **AddressBook System**.  
The application supports **REST API operations**, **validation**, **logging**, **exception handling**, and **environment-based configuration**.

The project is implemented incrementally using **UC1 → UC12**.

---

# Technologies Used

- Java 11 / 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Lombok
- Validation API
- Maven

---

# Project Structure

```
src
 └── main
     └── java
         └── com.bridgelabz.addressbookapp
             ├── controller
             ├── service
             ├── model
             ├── dto
             ├── exception
     └── resources
         ├── application.properties
         ├── application-dev.properties
         └── application-prod.properties
```

---

# UC1 — Create AddressBook REST Controller

## Objective

Create REST Controller with basic endpoints

## Features

- GET all contacts
- GET contact by ID
- POST new contact
- PUT update contact
- DELETE contact

## Components Created

- AddressBookController
- AddressBook Model
- AddressBookDTO

---

# UC2 — Introduce Service Layer

## Objective

Move business logic to Service Layer

## Features

- Controller calls Service
- Service manages AddressBook Data

## Components Created

- AddressBookService

---

# UC3 — Store AddressBook Data

## Objective

Store AddressBook Data in memory

## Features

- Use List to store data
- Implement CRUD operations

## Service Methods

- getAllContacts()
- getContactById()
- addContact()
- updateContact()
- deleteContact()

---

# UC4 — Test REST APIs

## GET

```
curl http://localhost:8080/addressbook
```

## GET by ID

```
curl http://localhost:8080/addressbook/1
```

## POST

```
curl -X POST http://localhost:8080/addressbook \
-H "Content-Type: application/json" \
-d '{"name":"John","city":"Chennai","state":"TN"}'
```

## PUT

```
curl -X PUT http://localhost:8080/addressbook/1 \
-H "Content-Type: application/json" \
-d '{"name":"John","city":"Chennai","state":"TN"}'
```

## DELETE

```
curl -X DELETE http://localhost:8080/addressbook/1
```

---

# UC5 — Use Lombok

## Objective

Reduce boilerplate code using Lombok

## Lombok Annotations

- @Data
- @AllArgsConstructor
- @NoArgsConstructor
- @Slf4j

## Dependency

```
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

---

# UC6 — Add Logging

## Objective

Add logging to application

## Logging Annotation

```
@Slf4j
```

## Example

```
log.info("Fetching contacts");
log.error("Error occurred");
```

---

# UC7 — Logging Configuration

## application-dev.properties

```
logging.level.root=INFO
logging.level.com.bridgelabz=DEBUG
logging.file.name=logs/dev.log
```

## application-prod.properties

```
logging.level.root=ERROR
logging.file.name=logs/prod.log
```

---

# UC8 — Database Configuration

## application.properties

```
spring.profiles.active=dev
```

## application-dev.properties

```
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

---

# UC9 — Add Validation

## DTO Validation

```
@NotBlank(message="Name cannot be empty")
@Pattern(
regexp="^[A-Za-z ]{2,}$",
message="Name must contain only alphabets"
)
private String name;
```

## Controller

```
@Valid @RequestBody AddressBookDTO
```

---

# UC10 — Validation Error Handling

## Global Exception Handler

```
@ControllerAdvice
public class GlobalExceptionHandler
```

## Handle

```
MethodArgumentNotValidException
```

## Response

```
{
 "message": "Validation Failed",
 "data": {
   "name": "Name cannot be empty"
 }
}
```

---

# UC11 — Custom Exception Handling

## Exception Class

```
AddressBookException
```

## Handler

```
@ExceptionHandler(AddressBookException.class)
```

---

# UC12 — Handle AddressBook ID Not Found

## Service

```
.orElseThrow(() ->
new AddressBookException("Contact not found"))
```

## Global Handler

```
@ExceptionHandler(AddressBookException.class)
```

## Response

```
{
 "message": "Contact not found",
 "data": null
}
```

---

# Final Features

After UC12 Application Supports

- REST APIs  
- Service Layer  
- DTO  
- Lombok  
- Logging  
- Environment Profiles  
- Validation  
- Global Exception Handling  
- Custom Exception Handling  

---

# Run Application

```
mvn spring-boot:run
```

---

# Author
Shivani S
```
