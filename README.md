Evaluación: JAVA

Descripción: 
Se desarrolla una aplicación con:
  -SpringBoot 3.0.6
  -Java 17
  -Base de datos H2, configurada desde hibernate.
  -Gradle 8.1.1
  
Esta expone una API RESTful de creación de usuarios.
Cada usuario tiene asociado un listado de teléfonos mas campos como "nombre", "correo" y constraseña".
Al solicitar información del usuario, la respuesta será id, fecha de creación, fecha de actualización, 
el último ingreso (en caso de nuevo usuario, va a coincidir con la
fecha de creación), token de acceso de la API y un campo isActive, 
donde indica si el usuario sigue habilitado dentro del sistema.
Incluye pruebas unitarias.

Funcionalidades:
Usuario : Crear, Obtener, Editar(solo se edita su correo) y 
          Cambiar su estado isActive en true para activo o fals para inactivo (delete).
Teléfono: Crear.

Para empezar:
1.- Clona el repositorio
2.- Para control de versiones es aconsejable utilizar SDKman
3.- Instala las dependencias
4.- Run proyecto

Feature:
creationStructure -> arquitectura base más desarrollo de lógica de negocio.
relation -> desarrollo de relación de entidades.
token -> desarrollo de token por medio de JWT, se deja finalmente opción de UUID.
Test -> desarrollo de test unitarios.
Swagger -> configuración de dependencias para swagger.

Documentación:
JWT
https://www.baeldung.com/java-json-web-tokens-jjwt
https://github.com/jwtk/jjwt

H2
https://www.baeldung.com/spring-boot-h2-database

UUID
https://www.baeldung.com/java-uuid

Swagger
https://swagger.io/

Autor: Beatriz López C.




