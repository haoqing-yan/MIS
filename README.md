# MIS (Management Information System)

A modern, modular management information system built with Spring Boot and Spring Cloud.

## Project Structure

The project is organized into several modules:

```
MIS/
├── mis-cloud/                 # Cloud-native modules
│   ├── mis-ai/               # AI service module
│   ├── mis-auth/             # Authentication service
│   ├── mis-common/           # Common utilities and components
│   ├── mis-gateway/          # API Gateway
│   ├── mis-monitor/          # System monitoring
│   └── mis-system/           # System management
├── sql/                      # Database scripts
│   ├── ai/                   # AI module tables
│   ├── auth/                 # Authentication tables
│   ├── common/               # Common tables
│   ├── monitor/              # Monitoring tables
│   └── system/               # System management tables
└── pom.xml                   # Parent project configuration
```

## Features

- **Authentication & Authorization**
  - User management
  - Role-based access control
  - JWT-based authentication
  - Menu and permission management

- **System Management**
  - Department management
  - Post management
  - System configuration
  - Dictionary management
  - Operation logs
  - Login logs

- **Monitoring**
  - Online users
  - Scheduled tasks
  - Server monitoring
  - System logs

- **AI Integration**
  - Support for multiple AI providers (OpenAI, Ollama)
  - Chat history tracking
  - Usage statistics
  - Model configuration management

## Prerequisites

- JDK 17 or later
- Maven 3.8 or later
- MySQL 8.0 or later
- Redis 6.0 or later (optional)
- Ollama (for local AI support)

## Quick Start

1. **Database Setup**
   ```bash
   # Create database
   mysql -u root -p
   CREATE DATABASE mis DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
   ```

2. **Initialize Database**
   ```bash
   # Execute SQL scripts in order
   mysql -u root -p mis < sql/common/01_common_tables.sql
   mysql -u root -p mis < sql/auth/01_auth_tables.sql
   mysql -u root -p mis < sql/system/01_system_tables.sql
   mysql -u root -p mis < sql/monitor/01_monitor_tables.sql
   mysql -u root -p mis < sql/ai/01_ai_tables.sql
   ```

3. **Build Project**
   ```bash
   mvn clean install
   ```

4. **Start Services**
   ```bash
   # Start gateway
   java -jar mis-gateway/target/mis-gateway-1.0.0.jar

   # Start auth service
   java -jar mis-auth/target/mis-auth-1.0.0.jar

   # Start system service
   java -jar mis-system/target/mis-system-1.0.0.jar

   # Start monitor service
   java -jar mis-monitor/target/mis-monitor-1.0.0.jar

   # Start AI service
   java -jar mis-ai/target/mis-ai-1.0.0.jar
   ```

## Configuration

### AI Service Configuration

1. **OpenAI Configuration**
   - Set environment variable: `OPENAI_API_KEY`
   - Configure in `mis-ai/src/main/resources/application.yml`:
     ```yaml
     spring:
       ai:
         openai:
           api-key: ${OPENAI_API_KEY}
           model: gpt-3.5-turbo
     ```

2. **Ollama Configuration**
   - Install and run Ollama locally
   - Configure in `mis-ai/src/main/resources/application.yml`:
     ```yaml
     spring:
       ai:
         ollama:
           base-url: http://localhost:11434
           model: llama2
     ```

## API Documentation

The system provides RESTful APIs for all services. API documentation is available at:

- Gateway: `http://localhost:8080/swagger-ui.html`
- Auth Service: `http://localhost:8081/swagger-ui.html`
- System Service: `http://localhost:8082/swagger-ui.html`
- Monitor Service: `http://localhost:8083/swagger-ui.html`
- AI Service: `http://localhost:8084/swagger-ui.html`

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. 