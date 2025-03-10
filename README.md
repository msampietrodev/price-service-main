# price-service

# Proyecto: API de Selección de Precios

## Descripción
Esta aplicación es una API RESTful desarrollada en **Spring Boot con WebFlux** para la selección de precios de productos en una determinada fecha de aplicación. Implementa **arquitectura hexagonal**, aplicando principios **SOLID** y **Clean Code**.

## Tecnologías Utilizadas
- **Java 21**
- **Spring Boot 3.4.0-SNAPSHOT** (WebFlux, R2DBC)
- **Reactor-resilience4j
- **H2** (Base de datos en memoria, R2DBC)
- **Lombok** (Reducción de boilerplate code)
- **MapStruct** (Conversión de DTOs)
- **JWT** (Autenticación)
- **ControllerAdvice** (Manejo global de excepciones)
- **JaCoCo** (Cobertura de pruebas)
- **SonarQube** (Análisis de calidad del código)
- **JUnit 5 y Reactor Test** (Pruebas unitarias y de integración)
- **Swagger (Springfox 3.0.0)** (Documentación de la API)
- **CI/CD** (GitHub Actions opcional)

## Arquitectura
Se sigue el patrón **Hexagonal (Ports & Adapters)**:
- **Application Layer**: Contiene la lógica de negocio en servicios.
- **Domain Layer**: Define entidades y lógica de dominio.
- **Infrastructure Layer**: Contiene adaptadores, persistencia y configuraciones.

## Endpoints
### **POST /select**
**Parámetros de entrada:**
```json
{
  "applicationDate": "2024-02-19T10:00:00Z",
  "productId": 35455,
  "brandId": 1
}
```
**Respuesta esperada:**
```json
{
  "productId": 35455,
  "brandId": 1,
  "price": 35.50,
  "startDate": "2024-02-19T00:00:00Z",
  "endDate": "2024-02-19T23:59:59Z"
}
```

## Pruebas
### Pruebas Unitarias e Integración
Se han implementado las siguientes pruebas:
1. **Petición a las 10:00 del día 14 del producto 35455 para la brand 1**
2. **Petición a las 16:00 del día 14 del producto 35455 para la brand 1**
3. **Petición a las 21:00 del día 14 del producto 35455 para la brand 1**
4. **Petición a las 10:00 del día 15 del producto 35455 para la brand 1**
5. **Petición a las 21:00 del día 16 del producto 35455 para la brand 1**
6. **Prueba de integración completa**
7. **Cobertura del núcleo DDD al 100%**

Se usa **JaCoCo** para garantizar una cobertura de pruebas mínima del **80%**.

## Manejo de Errores
Se utilizan diferentes códigos HTTP según el caso:
- **400 Bad Request**: Datos de entrada no válidos.
- **404 Not Found**: Precio no encontrado.
- **500 Internal Server Error**: Error inesperado.

## Estándares de Desarrollo
- **Commits bien separados**, siguiendo el estándar de nomenclatura (Convencional Commits):
  - `feat: Agrega selección de precio`
  - `fix: Corrige bug en validación de fechas`
  - `test: Agrega pruebas de integración`
- **Linter:** Análisis con **SonarQube** para evitar alertas.
- **CI/CD:** Se puede configurar con **GitHub Actions** para integración y despliegue automático.
