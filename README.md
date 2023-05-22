<h1 align="center"> Evaluación: JAVA </h1>



## 📓 Descripción:
Se desarrolla una aplicación con:
  <li>SpringBoot 3.0.6</li>
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
<p>POST http://localhost:8080/users </p>
<img width="500" alt="Captura de Pantalla 2023-05-19 a la(s) 12 22 32" src="https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/9f0e56ee-6846-4c55-ab4d-1f1cd836467a">
<br>
<p>POST</p>
![postman](https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/349934f4-73a4-4770-926c-2c2451a034c6)
<br>
<p>GET http://localhost:8080/users/{email} </p>
<img width="500" alt="Captura de Pantalla 2023-05-19 a la(s) 12 23 11" src="https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/15fb4984-74d1-475d-b1ff-f713859b50ee">
<br>
<p>PUT http://localhost:8080/users/{id} </p>
<img width="500" alt="Captura de Pantalla 2023-05-19 a la(s) 12 28 04" src="https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/d4fd9055-d488-4663-a74d-075c2f7188f5">
<p>DELETE http://localhost:8080/users/{id}/{email}</p>
<br>
<img width="500" alt="Captura de Pantalla 2023-05-19 a la(s) 12 27 49" src="https://github.com/Be-ri-lo/evaluacionJavaFinal/assets/67941274/cee4e98c-43e3-48f8-b581-e52c55d0e40a">


## 💼 Feature:
<li>creationStructure -> arquitectura base más desarrollo de lógica de negocio.</li>
<li>relation -> desarrollo de relación de entidades.</li>
<li>token -> desarrollo de token por medio de JWT, se deja finalmente opción de UUID.</li>
<li>Test -> desarrollo de test unitarios.</li>
<li>Swagger -> configuración de dependencias para swagger.</li>

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




