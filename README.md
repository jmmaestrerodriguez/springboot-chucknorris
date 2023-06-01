# Chuck Norris Jokes
Proyecto SpringBoot para una entrevista técnica de Babel-Openbank en la que nos conectamos a https://api.chucknorris.io/ para obtener chistes aleatorios.

## Stack tecnológico

* Java JDK 17
* SpringBoot 2
* Redis cache
* Docker

## Estructura
La estructura del proyecto es la siguiente:
```text
├───.mvn
│   └───wrapper
└───src
    ├───main
    │   ├───java
    │   │   └───com
    │   │       └───jmmaestre
    │   │           └───chucknorrisjokes
    │   │               ├───config
    │   │               ├───controller
    │   │               │   └───impl
    │   │               ├───dto
    │   │               ├───exception
    │   │               ├───feign
    │   │               │   ├───clients
    │   │               │   └───config
    │   │               ├───mapper
    │   │               ├───model
    │   │               ├───repository
    │   │               └───service
    │   └───resources
    └───test
        └───java
            └───com
                └───jmmaestre
                    └───chucknorrisjokes
                        ├───controller
                        ├───feign
                        └───service
```

## Funcionalidades
La funcionalidad principal de este proyecto es la de obtener chistes aleatorios de Chuck Norris y almacenarlos en una caché, para posteriormente poder obtenerlos mediante su ID.

## Puesta en marcha en local y funcionamiento
En primer lugar, ejecutaremos el siguiente comando en la carpeta raíz del proyecto, para construir el contenedor de Docker con la imagen de Redis:
```shell	
docker-compose up -d
```

Para poner en funcionamiento el microservicio, lo primero será construirlo. Para ello, ejecutaremos el siguiente comando en la carpeta raíz del proyecto:

```shell
mvn clean install
```

Una vez hecho lo anterior, podrá ser puesto en marcha usando el comando
```shel
mvn spring-boot:run
```
Finalmente, el microservicio se encontrará desplegado en http://localhost:8080/, o lo que es lo mismo, http://127.0.0.1:8080/

### Invocando a la API
Para invocar a la API, podemos usar una herramienta como Postman o CURL, pero se recomienda acceder a la interfaz visual provista por Swagger UI, y así obtener más información (especificación OpenAPI) acerca de los endpoints disponibles, los tipos de entrada aceptados en las solicitudes, y las respuestas.  

Para acceder al Swagger de la aplicación, basta con entrar a http://localhost:8080/v1/swagger-ui/index.html.

#### Ejemplos de llamada
* Obtener un chiste aleatorio
```shell
POST /v1/joke-request
```
* Obtener un chiste por ID (El id se obtiene de la llamada anterior)
```shell
GET /v1/joke/{id}
```

## Tests
De nuevo, usando el comando mvn, podremos ejecutar los tests mediante

```shell
mvn clean test
```

## Releases
Para facilitar la puesta en marcha del microservicio, es posible acceder al listado de releases del repositorio (https://github.com/jmmaestrerodriguez/springboot-chucknorris/releases), y descargar el fichero .jar de la última versión disponible. Una vez descargado, bastará con ejecutar el siguiente comando para ponerlo en marcha:

```shell
java -jar <nombreDelArchivo>.jar
```

**Nota:** Es necesario haber levantado el contenedor de Docker con Redis antes de ejecutar el comando anterior.
