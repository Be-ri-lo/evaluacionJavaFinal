<h1 align="center"> Evaluación: JAVA </h1>



<h3>📓 Descripción:</h3>
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

## :hammer: Funcionalidades:
<li>Usuario : Crear, Obtener, Editar(solo se edita su correo) y cambiar su estado isActive en true para activo o falso para inactivo (delete).
<li>Teléfono: Crear.</li>

<h3> 🖱️ Para empezar:</h3>
<ol>
  <li> Clona el repositorio </li>
  <li>Para control de versiones es aconsejable utilizar SDKman</li>
  <li>Instala las dependencias</li>
  <li>Run proyecto</li>
</ol>

<h3> 💼 Feature:</h3>
<li>creationStructure -> arquitectura base más desarrollo de lógica de negocio.</li>
<li>relation -> desarrollo de relación de entidades.</li>
<li>token -> desarrollo de token por medio de JWT, se deja finalmente opción de UUID.</li>
<li>Test -> desarrollo de test unitarios.</li>
<li>Swagger -> configuración de dependencias para swagger.</li>

  <h3> 📃 Documentación:</h3>
  <li>JWT</li>
https://www.baeldung.com/java-json-web-tokens-jjwt
https://github.com/jwtk/jjwt

  <li>H2</li>
https://www.baeldung.com/spring-boot-h2-database

  <li>UUID</li>
https://www.baeldung.com/java-uuid

  <li>Swagger</li>
https://swagger.io/

  <h4> 🧑‍🚀 Autor: Beatriz López C.</h4>




