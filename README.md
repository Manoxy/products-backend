
# Products API Backend 🚀

API RESTful desarrollada con **Spring Boot 3** y **Java 21** para la gestión de productos, autenticación de usuarios y configuraciones del sistema. Diseñada bajo principios de arquitectura limpia, manejo global de excepciones y seguridad mediante JWT.

---

## 🛠️ Tecnologías y Versiones

| Tecnología / Herramienta | Versión | Descripción |
| :--- | :--- | :--- |
| **Java** | `21` (LTS) | Lenguaje base con soporte para Virtual Threads y Pattern Matching |
| **Spring Boot** | `3.x` / `4.x` | Framework principal para el desarrollo de la API REST |
| **Spring Security** | `6.x` | Gestión de autenticación y autorización |
| **jjwt** | `0.12.5` | Generación y validación de tokens JWT |
| **Spring Data JPA** | `3.x` | Persistencia e interacción con base de datos mediante Hibernate |
| **H2 Database** | En memoria | Base de datos relacional para entornos de desarrollo y pruebas |
| **Maven** | `3.9+` | Gestor de dependencias y construcción |

---

## 🏗️ Arquitectura del Proyecto

El código está estructurado en capas bien definidas dentro de `com.example.api_backend`:

```text
src/main/java/com/example/api_backend/
├── config/         # Configuraciones de Seguridad (SecurityConfig), CORS y carga de datos iniciales
├── controller/     # Endpoints REST (AuthController, ProductoController, ConfigController)
├── dto/            # Data Transfer Objects para requests y responses
├── entity/         # Entidades JPA (Usuario, Rol, Producto, AppConfig)
├── exception/      # Manejo global de excepciones (GlobalExceptionHandler, ApiError)
├── repository/     # Repositorios JPA para acceso a datos
├── security/       # Filtros JWT (JwtFilter, JwtUtils, TokenBlacklistService)
└── service/        # Lógica de negocio y servicios