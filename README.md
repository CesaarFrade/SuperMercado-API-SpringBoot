# SuperMercado API

API REST para la gestión de un supermercado, desarrollada con **Spring Boot** y **Java 21**. Permite administrar productos, sucursales y ventas (con sus respectivos detalles) mediante endpoints CRUD.

## Tecnologías

- **Java 21**
- **Spring Boot 4.0.1**
- **Spring Web MVC**
- **Spring Data JPA / Hibernate**
- **H2 Database** (en memoria, modo MySQL)
- **MySQL Connector** (opcional para producción)
- **Lombok**
- **Maven**

## Estructura del proyecto

```
SuperMercado/
├── src/main/java/com/supermerket/SuperMercado/
│   ├── controller/   # Controladores REST
│   ├── service/      # Lógica de negocio
│   ├── repository/   # Acceso a datos (Spring Data JPA)
│   ├── model/        # Entidades JPA
│   ├── dto/          # Objetos de transferencia de datos
│   └── mapper/       # Conversión Entidad <-> DTO
└── src/main/resources/
    └── application.properties
```

### Arquitectura por capas

```
Controller → Service → Repository → H2 (JPA)
                ↘ Mapper → DTO
```

## Entidades

| Entidad      | Descripción                                  |
|--------------|----------------------------------------------|
| `Producto`   | Productos del supermercado (nombre, categoría, cantidad, precio). |
| `Sucursal`   | Sucursales (nombre, dirección).              |
| `Venta`      | Cabecera de venta (fecha, estado, total, sucursal). |
| `DetalleVenta` | Líneas de la venta (producto, cantidad, precio unitario). |

### Relaciones

- `Venta` **N:1** `Sucursal`
- `Venta` **1:N** `DetalleVenta` (cascade ALL, orphanRemoval)
- `DetalleVenta` **N:1** `Producto`

## Requisitos previos

- JDK 21
- Maven 3.9+

## Ejecución

Desde el directorio `SuperMercado/`:

```bash
mvn spring-boot:run
```

O bien, compilar y ejecutar el JAR:

```bash
mvn clean package
java -jar target/SuperMercado-0.0.1-SNAPSHOT.jar
```

La API estará disponible en `http://localhost:8080`.

### Consola H2

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:superdb`
- Usuario: `cesar`
- Contraseña: *(vacía)*

## Endpoints

### Productos

| Método | Endpoint                  | Descripción                  |
|--------|---------------------------|------------------------------|
| GET    | `/producto/get`           | Lista todos los productos.   |
| POST   | `/producto/save`          | Crea un producto.            |
| PUT    | `/producto/edit/{id}`     | Actualiza un producto.       |
| DELETE | `/producto/delete/{id}`   | Elimina un producto.         |

### Sucursales

| Método | Endpoint                  | Descripción                  |
|--------|---------------------------|------------------------------|
| GET    | `/sucursal/get`           | Lista todas las sucursales.  |
| POST   | `/sucursal/save`          | Crea una sucursal.           |
| PUT    | `/sucursal/edit/{id}`     | Actualiza una sucursal.      |
| DELETE | `/sucursal/delete/{id}`   | Elimina una sucursal.        |

### Ventas

| Método | Endpoint                  | Descripción                  |
|--------|---------------------------|------------------------------|
| GET    | `/venta/get`              | Lista todas las ventas.      |
| POST   | `/venta/save`             | Registra una venta con sus detalles. |
| PUT    | `/venta/edit/{id}`        | Actualiza una venta.         |
| DELETE | `/venta/delete/{id}`      | Elimina una venta.           |

## Ejemplos de peticiones

### Crear una sucursal

```json
POST /sucursal/save
{
  "nombre": "Sucursal Centro",
  "direccion": "Av. Principal 123"
}
```

### Crear un producto

```json
POST /producto/save
{
  "nombre": "Arroz",
  "categoria": "Abarrotes",
  "precio": 25.50,
  "cantidad": 100
}
```

### Registrar una venta

```json
POST /venta/save
{
  "fecha": "2026-08-11",
  "estado": "COMPLETADA",
  "idSucursal": 1,
  "detalles": [
    {
      "nombreProducto": "Arroz",
      "cantProducto": 3,
      "precio": 25.50
    }
  ]
}
```

## Configuración de la base de datos

Por defecto se usa una base de datos **H2 en memoria** (los datos se pierden al reiniciar). Para usar **MySQL**, modifica `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/supermercado
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

> El esquema de tablas se crea automáticamente (`ddl-auto=update`).

## Recursos adicionales

- `DIagramaClases.drawio.png` — diagrama de clases del proyecto.
- `My Collection.postman_collection.json` — colección de Postman con ejemplos de peticiones.
