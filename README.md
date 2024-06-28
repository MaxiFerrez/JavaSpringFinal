# Trabajo Final de Backend

## Autor
Ferrez Maximiliano

## Descripción
Este proyecto de backend está desarrollado en Java utilizando Spring Boot. Proporciona una API para la gestión de productos y órdenes.

## Endpoints

### Products
Base URL: `http://localhost:8083`

- **GET /products**
  - Descripción: Este endpoint trae la lista de los productos.
  - Ejemplo de respuesta:
    ```json
    [
      {
        "id": 1,
        "sku": "ABC123",
        "name": "Producto 1",
        "description": "Descripción del Producto 1",
        "price": 100.0,
        "status": true
      },
      {
        "id": 2,
        "sku": "DEF456",
        "name": "Producto 2",
        "description": "Descripción del Producto 2",
        "price": 150.0,
        "status": true
      }
    ]
    ```

- **POST /products**
  - Descripción: Este endpoint carga un nuevo producto.
  - Ejemplo de cuerpo de solicitud:
    ```json
    {
      "sku": "GHI789",
      "name": "Producto 3",
      "description": "Descripción del Producto 3",
      "price": 200.0,
      "status": true
    }
    ```
  - Ejemplo de respuesta:
    ```json
    {
      "id": 3,
      "sku": "GHI789",
      "name": "Producto 3",
      "description": "Descripción del Producto 3",
      "price": 200.0,
      "status": true
    }
    ```

- **PUT /products/{id}**
  - Descripción: Este endpoint actualiza un producto existente.
  - Ejemplo de cuerpo de solicitud:
    ```json
    {
      "sku": "GHI789",
      "name": "Producto 3 actualizado",
      "description": "Descripción del Producto 3 actualizada",
      "price": 210.0,
      "status": false
    }
    ```
  - Ejemplo de respuesta:
    ```json
    {
      "id": 3,
      "sku": "GHI789",
      "name": "Producto 3 actualizado",
      "description": "Descripción del Producto 3 actualizada",
      "price": 210.0,
      "status": false
    }
    ```

- **DELETE /products/{id}**
  - Descripción: Este endpoint elimina un producto existente.
  - Ejemplo de respuesta:
    ```json
    {
      "message": "Producto eliminado correctamente"
    }
    ```

### Orders
Base URL: `http://localhost:8083`

- **GET /orders**
  - Descripción: Este endpoint trae la lista de las órdenes.
  - Ejemplo de respuesta:
    ```json
    [
      {
        "id": 1,
        "productId": 1,
        "quantity": 2,
        "totalPrice": 200.0
      },
      {
        "id": 2,
        "productId": 2,
        "quantity": 1,
        "totalPrice": 150.0
      }
    ]
    ```

- **POST /orders**
  - Descripción: Este endpoint crea una nueva orden.
  - Ejemplo de cuerpo de solicitud:
    ```json
    {
      "productId": 1,
      "quantity": 2,
      "totalPrice": 200.0
    }
    ```
  - Ejemplo de respuesta:
    ```json
    {
      "id": 3,
      "productId": 1,
      "quantity": 2,
      "totalPrice": 200.0
    }
    ```

- **PUT /orders/{id}**
  - Descripción: Este endpoint actualiza una orden existente.
  - Ejemplo de cuerpo de solicitud:
    ```json
    {
      "productId": 1,
      "quantity": 3,
      "totalPrice": 300.0
    }
    ```
  - Ejemplo de respuesta:
    ```json
    {
      "id": 3,
      "productId": 1,
      "quantity": 3,
      "totalPrice": 300.0
    }
    ```

- **DELETE /orders/{id}**
  - Descripción: Este endpoint elimina una orden existente.
  - Ejemplo de respuesta:
    ```json
    {
      "message": "Orden eliminada correctamente"
    }
    ```

## Requisitos

- Java 11 o superior
- Maven
- Spring Boot

## Instrucciones para Ejecutar

1. Clona el repositorio:
    ```bash
    git clone https://github.com/MaxiFerrez/JavaSpringFinal.git
    ```

2. Navega al directorio del proyecto:
    ```bash
    cd microservicios
    ```

3. Compila y ejecuta la aplicación:
    ```bash
    mvn spring-boot:run
    ```

4. Accede a la documentación de Swagger UI en `http://localhost:8083/swagger-ui.html` para Productos y `http://localhost:8082/swagger-ui.html` para Ordenes

## Contacto
Si tienes alguna pregunta o sugerencia, no dudes en contactarme a maxiferrez@gmail.com
