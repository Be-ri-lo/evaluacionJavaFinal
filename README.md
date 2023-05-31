<h1 align="center"> Evaluación: JAVA </h1>



## 📓 Descripción:
Se desarrolla una aplicación con: [actualización]
<li>SpringBoot 3.0.6 -> SpringBoot 2.7.12 </li>
  
<h6>Al configurar Swagger, por incompatibilidad de versiones de spring boot 3.x con springfox, se debe cambiar de versión a 2.7.12 para poder utilizar la dependencia springfox.
Spring Boot 3.0 está diseñado para Java17 y JakartaEE, no para JavaEE. Spring Boot 3 todavía está en desarrollo y aún no ha visto un lanzamiento final.
Una de las soluciones para no bajar a versiones inferiores era sustituir la dependencia springfox por spring-doc-openapi-starter-webm vc-ui, pero está de igual forma tuvo problemas.</h6>
  <li>Java 17</li>
  <li>Base de datos H2, configurada desde hibernate.</li>
  <li>Gradle 8.1.1</li>
 <br> 
Esta expone una API RESTful de creación de usuarios.
Cada usuario tiene asociado un listado de teléfonos mas campos como "nombre", "correo" y constraseña".
Al solicitar información del usuario, la respuesta será id, fecha de creación, fecha de actualización, 
el último ingreso (en caso de nuevo usuario, va a coincidir con la
fecha de creación), token de acceso de la API y un campo isActive, 
donde indica si el usuario sigue habilitado dentro del sistema.
Incluye pruebas unitarias.

## 🏗️ Diagrama :

![Blank diagram - Database ER diagram (crow's foot)](https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/a9ab1dd2-3ca5-4317-b051-d007e9aff966)

## :hammer: Funcionalidades:
<li>Usuario : Crear, Obtener, Editar(solo se edita su correo) y cambiar su estado isActive en true para activo o falso para inactivo (delete).
<li>Teléfono: Crear.</li>

## 🖱️ Para empezar:
<ol>
  <li> Clona el repositorio </li>
  Clone git@github.com:Be-ri-lo/evaluacionJavaFinal.git
  <li>Para control de versiones es aconsejable utilizar SDKman</li>
  https://sdkman.io/
  <li>Instala las dependencias</li>
  <li>Run proyecto</li>
</ol>

## 🔡 Conexión Base de Datos:
Se debe ingresar a "localhost:8080/h2-console"
Completar con los siguientes datos: 
<li> JDBC url = jdbc:h2:mem:test </li>
<li> user name=h2 </li>
<li> password=password </li>

![Captura de Pantalla 2023-05-19 a la(s) 19 16 47](https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/50b5c107-2440-4344-9a34-c39873b3596e)


## 🧪 Pruebas:

## SWAGGER: 
<p> correr app y desde navegador utilizar siguiente link.</p>
<h6> http://localhost:8080/swagger-ui/index.html </h6>
<li> Body para una mejor prueba </li>
<h6> {
    "name": "nombre",
    "email": "example@mail.com",
    "password": "Pass1234!!",
    "phones": [
        {
        "phoneNumber":"123456789",
        "cityCode":"2",
        "countryCode":"1"
        }
    ]
} </h6>
<br>

## POSTMAN:
<br>
<p>POST http://localhost:8080/users </p>
<img width="500" alt="post" src="https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/796c3f97-c4d7-46e1-b263-96d5e8444640">

<br>
<h6> </h6>
<p>GET http://localhost:8080/users/{id} </p>
<img width="500" alt="getId" src="https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/3882b036-97af-4189-b90b-7fedb665be3a">

<br>
<h6> </h6>
<p>GET http://localhost:8080/users/email/{email} </p>
<img width="500" alt="getEmail" src="https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/a102e809-c7ba-4c3b-9a62-71c2b9d6dd78">

<br>
<h6> </h6>
<p>GET http://localhost:8080/users/all </p>
<img width="500" alt="getAll" src="https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/069e9580-308a-446c-bcc4-5ffc665d6aed">

<br>
<h6> </h6>
<p>PUT http://localhost:8080/users/{id} </p>
<img width="500" alt="put" src="https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/79c33941-9aed-4431-a87a-f1b03c9f0c68">

<br>
<h6> </h6>
<p>DELETE http://localhost:8080/users/{email}</p>
<img width="500" alt="delete" src="https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/3a6fa5d3-638a-4003-bc3b-86210b19f2f0">


## 💼 Feature:
<li>creationStructure -> arquitectura base más desarrollo de lógica de negocio.</li>
<li>relationfixing -> desarrollo de relación de entidades.</li>
<li>JWT -> desarrollo de token por medio de JWT.</li>
<li>Test -> desarrollo de test unitarios.</li>
<li>SwaggerConfig -> configuración de dependencias para swagger.</li>

## 📃 Documentación:
  <li>JWT</li>
https://www.baeldung.com/java-json-web-tokens-jjwt
https://github.com/jwtk/jjwt

  <li>H2</li>
https://www.baeldung.com/spring-boot-h2-database

  <li>UUID</li>
https://www.baeldung.com/java-uuid

  <li>Swagger</li>
https://swagger.io/

### 🧑‍🚀 Autor: Beatriz López C.




