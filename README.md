# BankTech Backend

Backend de ejemplo para un sistema bancario desarrollado en **Spring Boot** **H2 Database** **MapStruct** y arquitectura **hexagonal**.

## 📂 Estructura del Proyecto

```
bank-hexagonal/
 ├── domain/
 │    └── model/            # Entidades del dominio (Customer Account)
 ├── application/
 │    ├── ports/            # Interfaces (repositorios)
 │    └── usecases/         # Lógica de negocio (servicios del dominio)
 ├── infrastructure/
 │    ├── adapters/
 │    │     ├── repository/ # Implementaciones JPA + Adapters
 │    │     └── mapper/     # MapStruct DTO ↔ Entidad
 │    ├── controller/       # Controladores REST
 │    └── config/           # Configuración (H2 beans etc.)
 └── BankHexagonalApplication.java
```

---

## ⚙ Tecnologías

- Java 21
- Spring Boot 3.5.x
- Spring Data JPA
- H2 Database (runtime)
- MapStruct (DTO ↔ entidad)
- Lombok
- Jakarta Validation
- Maven

---

## 🏗 Arquitectura y Patrones

- **Hexagonal Architecture**: separación entre dominio aplicación e infraestructura.
- **DDD (Domain-Driven Design)**: entidades (`Customer` `Account`) aisladas del resto de capas.
- **Ports & Adapters**: use cases dependen de interfaces (ports) los adapters implementan JPA.
- **DTOs y mappers**: separan API pública del dominio.

---

## ✅ Principios y Buenas Prácticas Aplicadas

### **SOLID**: Cada clase debe tener una única responsabilidad o motivo de cambio.
- **SRP**: Cada clase tiene una única responsabilidad (UseCase Controller Mapper).
- **OCP**: Se pueden añadir funcionalidades sin modificar la lógica central.
- **LSP**: Controladores extienden `BaseController` sin alterar comportamiento.
- **ISP**: Interfaces pequeñas y específicas (`CustomerRepositoryPort` `AccountRepositoryPort`).
- **DIP**: UseCases dependen de abstracciones no de implementaciones concretas.

### **DRY**: Evitar la duplicación de código y lógica.
- Método `respond()` en `BaseController` para centralizar respuestas.
- MapStruct evita duplicar código de conversión DTO ↔ entidad.
- Mensajes centralizados (`ResponseMessages` `ValidationMessages` `ExceptionMessages`) evitan repetición.

### **Buenas prácticas**
- Validación de entrada con `@Valid` en DTOs.
- Logging estructurado con `@Slf4j` en UseCases.
- ControllerAdvice global para manejo consistente de errores.

### **DDD**: Organizar el código alrededor del dominio del negocio, separando las responsabilidades en capas coherentes.
- Entidades de dominio (`Customer` `Account`) independientes.
- UseCases para lógica de negocio.
- Ports para abstraer repositorios.
- Adapters para infraestructura.

---

## 🧩 Mensajes Centralizados

- **ResponseMessages** → Mensajes de éxito.
- **ValidationMessages** → Mensajes de validación de entrada.
- **ExceptionMessages** → Mensajes de error y excepciones.

---

## 🚀 Endpoints principales

### Customers
- `POST /api/customers` → Crear cliente
- `GET /api/customers` → Obtener todos los clientes
- `GET /api/customers/{id}` → Obtener cliente por ID
- `PUT /api/customers/{id}` → Actualizar cliente
- `DELETE /api/customers/{id}` → Eliminar cliente

### Accounts
- `POST /api/accounts` → Crear cuenta
- `GET /api/accounts` → Obtener todas las cuentas
- `GET /api/accounts/{id}` → Obtener cuenta por ID
- `PUT /api/accounts/{id}` → Actualizar cuenta
- `DELETE /api/accounts/{id}` → Eliminar cuenta

---

## 🧪 Validación y manejo de errores

- Uso de **DTOs y anotaciones de validación** para evitar datos incorrectos.
- Errores estandarizados en **JSON** usando `ApiResponse` y `GlobalExceptionHandler`.

Ejemplo de error:

```json
{
  \success\: false,
  \message\: \Validation failed\,
  \data\: {
    \firstName\: \First name is required\,
    \lastName\: \Last name is required\
  }
}
```

---

## 📝 Ejemplo de request para crear un cliente

```json
{
  \firstName\: \John\,
  \lastName\: \Doe\,
  \email\: \john.doe@example.com\,
  \dni\: \12345678A\
}
```

---

## 📄 Documentación de la API (Swagger / OpenAPI)

La documentación interactiva de la API se puede consultar en:

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Esta documentación incluye:

- Todos los endpoints de Customers y Accounts
- Modelos de datos (DTOs)
- Validaciones de entrada
- Ejemplos de request/response

Para extraer el JSON para uso en frontend:

```bash
curl http://localhost:8080/v3/api-docs -o openapi.json
```

---

## 📚 Documentación de código (Javadoc)

Se genera automáticamente con Maven:

```bash
mvn javadoc:javadoc
```

- Los Javadocs incluyen:
  - Explicación de clases (`UseCases`, `Controllers`, `DTOs`)
  - Descripción de métodos públicos y parámetros
  - Notas sobre validaciones y respuestas de API

El resultado se encuentra en: `target/site/apidocs/index.html`

---

## 📌 Conclusión

Esta aplicación sigue buenas prácticas de **arquitectura diseño y principios SOLID** asegurando que sea **escalable mantenible y testeable** mientras separa **dominio lógica de negocio y capa de presentación**.