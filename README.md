# prueba_servicio_rest

Codigo de la prueba del servicio Rest.

## Instalar la BD

la base de datos fue creada en PHPmyAdmin, simplemente se debe crear una base de datos llamada prueba y se ejecuta el Script; el Script Sql se encuentra en la carpeta **adicional**, con el nombre de **BD.sql**.

## Configurar la coneccion a la BD
  
 En el caso que se desee cambiar el nombre a la BD o sucede algun problema, la configuracion de la coneccion se encuentra en el archivo **application.properties**(src/main/resources/) dentro del codigo. la configuracion actual son las siguientes lineas:
 
 ```
 spring.datasource.url=jdbc:mysql://localhost/prueba?useSSL=false
spring.datasource.dbname=prueba
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## POSTMAN

Dentro de la carpeta adicional se encuentra tambien el archivo Postman **TAREA.postman_collection.json** con la invocacion de los servicios y un ejemplo para su llamado.


## URIs

**crearUsuario:** crea un nuevo usuario.
**Requisitos:** solo crea un usuario; si el correo no se ha ingresado previamente y el mail sigue el siguiente formato **nombre@serverCorreo.cl**; Ademas el password debe tener mayusculas, minusculas y signos.

**POST** http://localhost:8080/usuario

**getUsuario:** Obtiene un usuario ya registrado.

**GET** http://localhost:8080/usuario/{id}

**actualizarUsuario:** actualiza campos del registro.

**PATCH** http://localhost:8080/usuario/{id}

**eliminaUsuario:** actualiza campos del registro.

**DELETE** http://localhost:8080/usuario/{id}
