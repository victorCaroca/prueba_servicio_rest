# prueba_servicio_rest

Código de la prueba del servicio Rest.

## Instalar la BD

la base de datos fue creada en PHPmyAdmin, simplemente se debe crear una base de datos llamada prueba y se ejecuta el Script; el Script Sql se encuentra en la carpeta **adicional**, con el nombre de **BD.sql**.

## Configurar la coneccion a la BD

En el caso que se desee cambiar el nombre a la BD o sucede algún problema, la configuración de la coneccion se encuentra en el archivo **application.properties**(src/main/resources/) dentro del código. la configuración actual son las siguientes líneas:

```
spring.datasource.url=jdbc:mysql://localhost/prueba?useSSL=false
spring.datasource.dbname=prueba
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## POSTMAN

Dentro de la carpeta adicional se encuentra también el archivo Postman **TAREA.postman_collection.json** con la invocación de los servicios y un ejemplo para su llamado.


## URIs

**crearUsuario:** crea un nuevo usuario.

**Requisitos:** solo crea un usuario; si el correo no se ha ingresado previamente y el mail sigue el siguiente formato **nombre@serverCorreo.cl**, Además el password debe tener mayúsculas, minúsculas y signos.

**POST** http://localhost:8080/usuario

**getUsuario:** Obtiene un usuario ya registrado.

**GET** http://localhost:8080/usuario/{id}

**actualizarUsuario:** Actualiza campos del registro.

**PATCH** http://localhost:8080/usuario/{id}

**eliminaUsuario:** Actualiza campos del registro.
