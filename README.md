# API IMC — Java Servlet

API REST educativa desarrollada con **Java JDK 21, Java EE 8, Maven, Gson 2.13.1 y Apache Tomcat 9** para calcular el Índice de Masa Corporal (IMC).

## 1. Funcionalidad

La API recibe una petición `POST` con datos JSON:

```json
{
  "nombre": "Carlos",
  "peso": 95,
  "altura": 1.75
}
```

Calcula:

```text
IMC = peso / (altura * altura)
```

Clasificación:

| IMC | Clasificación |
|---:|---|
| < 18.5 | Bajo peso |
| 18.5 - < 25.0 | Peso normal |
| 25.0 - < 30.0 | Sobrepeso |
| >= 30.0 | Obesidad |

## 2. Endpoint

```text
POST http://localhost:8080/ApiIMC/api/imc
```

El endpoint está definido en `ImcServlet`:

```java
@WebServlet(
    name = "ImcServlet",
    urlPatterns = {"/api/imc"}
)
```

## 3. Tecnologías

- Java JDK 21
- Java EE 8
- `javax.servlet`
- Maven
- Apache Tomcat 9
- Gson 2.13.1
- Postman

## 4. Estructura

```text
ApiIMC
├── src/main/java
│   ├── controlador
│   │   └── ImcServlet.java
│   └── modelo
│       └── DatosImc.java
├── src/main/webapp
│   └── WEB-INF
│       └── web.xml
└── pom.xml
```

## 5. Gson

En `pom.xml`:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.13.1</version>
</dependency>
```

Gson permite convertir el JSON recibido en un objeto Java:

```text
JSON → Gson → DatosImc
```

El Servlet utiliza `request.getReader()` para leer el Body y `gson.fromJson(...)` para convertirlo.

## 6. Prueba con Postman

1. Seleccionar `POST`.
2. Usar la URL del endpoint.
3. En Headers establecer:

```text
Content-Type: application/json
```

4. En `Body → raw → JSON` enviar:

```json
{
  "nombre": "Ana",
  "peso": 60,
  "altura": 1.65
}
```

5. Presionar **Send**.

## 7. Respuesta actual

La API responde actualmente `text/plain`:

```text
Nombre: Ana
Peso: 60.0
Altura: 1.65
IMC: 22.03856749311295
Clasificación: Peso normal
```

## 8. Flujo de funcionamiento

```text
Postman
   ↓
HTTP POST
   ↓
/api/imc
   ↓
ImcServlet
   ↓
request.getReader()
   ↓
Gson
   ↓
DatosImc
   ↓
Cálculo del IMC
   ↓
Clasificación
   ↓
Respuesta HTTP
```

## 9. Parámetros vs JSON

Con parámetros:

```text
/api/imc?nombre=Ana&peso=60&altura=1.65
```

se utiliza:

```java
request.getParameter("nombre");
```

Con JSON:

```json
{
  "nombre": "Ana",
  "peso": 60,
  "altura": 1.65
}
```

se utiliza `request.getReader()` y Gson.

## 10. Códigos HTTP

| Código | Significado |
|---:|---|
| 200 | Petición correcta |
| 400 | Datos de entrada incorrectos |
| 404 | Endpoint o recurso no encontrado |
| 405 | Método HTTP no permitido |
| 500 | Error interno del servidor |

## 11. Despliegue

Construir el proyecto:

```bat
mvn clean package
```

El WAR se genera en:

```text
target/
```

Puede copiarse a:

```text
apache-tomcat-9.0.120\webapps
```

Para ejecutar Tomcat mostrando los mensajes en consola:

```bat
catalina.bat run
```

## 12. Próximas mejoras

- Devolver la respuesta en JSON.
- Validar los datos recibidos.
- Manejar errores `400`, `404`, `405` y `500` de forma controlada.
- Separar la lógica de negocio en `ImcService`.
- Crear un objeto `ResultadoImc`.
- Conectar posteriormente con MySQL.
- Documentar la API con OpenAPI/Swagger.
- Permitir el consumo desde un frontend.

## 13. Objetivo pedagógico

El proyecto permite comprender:

```text
API
 ↓
HTTP
 ↓
Endpoint
 ↓
POST
 ↓
JSON
 ↓
Servlet
 ↓
Gson
 ↓
Modelo Java
 ↓
Lógica de negocio
 ↓
Respuesta
```

> **Nota:** Esta documentación corresponde únicamente a la API IMC en Java Servlet. No incluye la integración con React.
