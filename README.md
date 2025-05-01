# MercadoGratis

## Instalacion

Clonar el repositorio.

instalar mvn en la terminal

y ejecutar los siguientes comandos:

```
mvn clean install
```

## Test

Ejecucion de test
```
mvn clean test
```

## Ejecucion

Ejecucion de Spring Boot

```
mvn spring-boot:run

```
## **Documento de Arquitectura**

Este documento define la arquitectura del proyecto **ArqII2** siguiendo el **Modelo C4** y diagramas UML.

---

### **1\. Introducción**

El presente Trabajo Práctico de **Arquitectura de Software II** corresponde a la **Entrega I** del semestre 2025s1 y tiene como objetivo **modelar e implementar** una **plataforma de compra y venta de productos** (similar a MercadoLibre) siguiendo el **patrón de Arquitectura Hexagonal** y los principios de **Domain-Driven Design (DDD)**.

En esta primera entrega se abordarán los siguientes aspectos:

* **Definición del Modelo de Dominio**: Identificación de entidades, agregados, raíces de agregado y objetos de valor según el Apéndice I.  
* **Implementación de la Lógica de Negocio**: Servicios que materializan los casos de uso.  
* **Diseño de Puertos y Adaptadores**: Interfaces de entrada/salida (use cases) y sus implementaciones en controladores, mappers y repositorios.  
* **Persistencia de Datos en MongoDB Atlas**: Uso de Spring Data MongoDB para almacenar usuarios, vendedores, productos, ventas y cuentas de dinero.  
* **Exposición de un único componente** vía API REST con las operaciones:  
  * Crear, eliminar y modificar **usuarios**  
  * Crear, eliminar y modificar **vendedores**  
  * Crear, eliminar y modificar **productos**  
  * Procesar la **venta** de un producto  
* **Entregables** de esta etapa:  
  * Solución funcionando en ambiente local  
  * Código fuente en repositorio accesible  
  * Archivo README con instrucciones de ejecución  
  * **Tests unitarios** para el dominio  
  * **Diagrama de clases UML** diferenciando Dominio, Puertos y Adaptadores  
  * Documentación de los casos de uso mediante **diagramas de secuencia**

### **2\. Modelo C4**

#### **2.1 Diagrama de Contexto**

Este diagrama muestra los actores externos que interactúan con el sistema y los sistemas externos involucrados en la plataforma **MercadoGratis**.

**Actores Externos:**

* **Comprador:** Usuario final que navega el catálogo, añade productos al carrito, realiza compras y consulta su saldo.  
* **Vendedor:** Usuario que publica, modifica y elimina productos, y revisa sus ventas.  
* **Administrador:** Usuario con privilegios para gestionar usuarios, vendedores y productos.

**Sistemas Externos:**

* **MongoDB Atlas:** Base de datos NoSQL que persiste entidades de usuarios, productos, compras y cuentas.  
* **Servicio de Email SMTP:** Envío de notificaciones de registro, restablecimiento de contraseña y confirmaciones de compra.

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/context.png?raw=true "This is a sample image.")

**Diagrama de Contenedores**

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/Containers.png?raw=true )

**Diagrama de Componentes**

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/components.png?raw=true )

**Diagrama de Clases**

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/erd.png?raw=true )

**Diagramas de Secuencias**

***Diagramas de addFunds***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/addFunds.png?raw=true )

***Diagramas de addUser***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/addUser.png?raw=true )

***Diagramas de changePassword***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/changePassword.png?raw=true )

***Diagramas de deleteProduct***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/deleteProduct.png?raw=true )

***Diagramas de forgetPassword***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/forgetPassword.png?raw=true )

***Diagramas de getFunds***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/getFunds.png?raw=true )

***Diagramas de listproduct***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/listproduct.png?raw=true )

***Diagramas de login***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/login.png?raw=true )

***Diagramas de purchase_diagram***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/purchase_diagram.png?raw=true )

***Diagramas de saveProduct***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/saveProduct.png?raw=true )

***Diagramas de searchProduct***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/searchProduct.png?raw=true )

***Diagramas de updateProduct***

![This is an alt text.](https://github.com/DiazMaxiM/ArqII/blob/main/docs/diagrams/updateProduct.png?raw=true )

## API - REST 

```bash
# 1. addUser
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "juan@example.com",
    "password": "miPass123",
    "name": "Juan Pérez"
  }'

# 2. login
curl -X POST http://localhost:8080/users/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "juan@example.com",
    "password": "miPass123"
  }'

# 3. forgetPassword
curl -X POST http://localhost:8080/users/forget-password \
  -H "Content-Type: application/json" \
  -d '{
    "email": "juan@example.com"
  }'

# 4. changePassword
curl -X POST http://localhost:8080/users/change-password \
  -H "Content-Type: application/json" \
  -d '{
    "email": "juan@example.com",
    "password": "miPass123",
    "newPassword": "nuevaPass456"
  }'

# 5. createPurchase
curl -X POST http://localhost:8080/purchases \
  -H "Content-Type: application/json" \
  -d '{
    "productId": 1,
    "buyerEmail": "juan@example.com",
    "quantity": 2
  }'

# 6. listPurchases
curl "http://localhost:8080/purchases?buyerEmail=juan@example.com"

# 7. getProduct
curl "http://localhost:8080/products/1"

# 8. deleteProduct
curl -X DELETE "http://localhost:8080/products/1"

# 9. saveProduct
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Producto XYZ",
    "price": 150.0,
    "seller": "vendedor@example.com",
    "stock": 10
  }'

# 10. updateProduct
curl -X PUT http://localhost:8080/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "name": "Producto XYZ (edición)",
    "price": 175.0,
    "seller": "vendedor@example.com",
    "stock": 8
  }'

# 11. listProducts
curl "http://localhost:8080/products?seller=vendedor@example.com"

# 12. searchProduct
curl -X POST http://localhost:8080/products/search \
  -H "Content-Type: application/json" \
  -d '{
    "query": "XYZ",
    "minPrice": 100.0,
    "maxPrice": 200.0,
    "seller": "vendedor@example.com"
  }'

# 13. getFunds
curl "http://localhost:8080/accounts/1/funds"

# 14. addFunds
curl -X POST http://localhost:8080/accounts/1/funds \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 50.0
  }'
