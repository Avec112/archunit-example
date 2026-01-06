# ArchUnit Example Project

This project demonstrates how to use **ArchUnit** to enforce architectural and coding rules in a Java application built with Vaadin and Spring Boot.

## Purpose

The main goal of this project is to showcase how ArchUnit can be used to:
1.  **Enforce Dependency Rules:** Ensure that the domain layer does not depend on the UI or external frameworks like Vaadin.
2.  **Enforce Coding Rules:** Prevent the use of standard output (e.g., `System.out`) or generic exceptions.

## Architecture Rules

The architectural rules are defined in `src/test/java/io/github/avec112/archunit/LayeredArchitectureTest.java`.

### Dependency Rule: Domain should not depend on UI or Vaadin
Classes in the `..domain..` package must not depend on classes in `com.vaadin..` or `..ui..`.

```java
ArchRule rule = ArchRuleDefinition.noClasses()
        .that().resideInAPackage("..domain..")
        .should().dependOnClassesThat().resideInAnyPackage("com.vaadin..", "..ui..");
```

## Coding Rules

Coding rules are defined in `src/test/java/io/github/avec112/archunit/CodingRulesTest.java`.

These tests ensure that:
*   No classes use `System.out` or `System.err`.
*   No classes use generic exceptions (`Exception`, `RuntimeException`, `Throwable`).
*   No classes use `printStackTrace()`.

## Running Tests

To run the ArchUnit tests, you can use Maven:

```bash
mvn test
```

Or run the test classes (`LayeredArchitectureTest`, `CodingRulesTest`, `DepencendyRulesTest`) directly from your IDE.

## Running the Application

To run the Vaadin application:
1.  Run `mvn spring-boot:run` from the terminal.
2.  Or run the `Application.java` class from your IDE.

Access the application at [http://localhost:8080/](http://localhost:8080/).

## Integration Tests

Integration tests (`GreetViewIT`) require a running server.
Run them using the `it` profile:

```bash
mvn verify -Pit
```

## Tech Stack
*   Java 21
*   Spring Boot 4
*   Vaadin 25
*   ArchUnit 1.4.1
*   Lombok (for logging and boilerplate)
