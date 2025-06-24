# Demo Spring Boot Application

A simple Spring Boot application with Spring Web dependency.

## Features

- Spring Boot 2.7.18
- Spring Web for REST API endpoints
- Maven build system
- Java 8 compatible

## Endpoints

- `GET /` - Returns a welcome message
- `GET /health` - Returns application health status

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6+ (or use the Maven wrapper)

### Running the Application

1. Navigate to the project directory:
   ```bash
   cd Demo
   ```

2. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```

   Or if you prefer to build and run the JAR:
   ```bash
   mvn clean package
   java -jar target/demo-0.0.1-SNAPSHOT.jar
   ```

3. The application will start on `http://localhost:8080`

### Testing the Application

Once the application is running, you can test the endpoints:

- Open your browser and go to `http://localhost:8080` to see the welcome message
- Visit `http://localhost:8080/health` to check the health status

Or use curl:
```bash
curl http://localhost:8080
curl http://localhost:8080/health
```

## Project Structure

```
Demo/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── DemoApplication.java      # Main application class
│   │   │   └── HelloController.java      # REST controller
│   │   └── resources/
│   │       └── application.properties    # Configuration file
│   └── test/
│       └── java/com/example/demo/
│           └── DemoApplicationTests.java # Test class
└── pom.xml                               # Maven configuration
``` 