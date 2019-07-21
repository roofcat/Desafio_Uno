# Desafío Uno

	* Creado por Christian Rojas: christiandiegor@gmail.com
 
## Pasos para ejecutar actividad

### Requisitos

[Descargar proyecto GDD](https://github.com/previred/Generador_Datos_Desafio_Uno)

	Desde una consola ejecutar:
		* cd Generador_Datos_Desafio_Uno-master/ApiPeriodos
		* mvn clean install -DskipTests
		* java -jar target/api-periodos-1.0.0.jar
	El servicio disponible es: GET http://127.0.0.1:8080/periodos/api
		Ejemplo de ejecución: curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8080/periodos/api'

### Ejecutar solución

	Descargar proyecto desafio-crojas
	Ejecutar desde una consola:
		* cd desafio-crojas
		* mvn clean install -DskipTests
		* java -jar target/desafio-crojas.jar
	La documentación de los servicios se encuentra en: http://localhost:8081/swagger-ui.html
		* Servicio 1: http://localhost:8081/challenges/v1/proxy: Este servicio es un proxy del proyecto GDD, es decir llamará y retornara el json de respuesta sin intervenir el objeto.
		* Servicio 2: http://localhost:8081/challenges/v1/solucion: Este servicio es la SOLUCIÓN al desafío solicitado por Previred en donde llamará al servicio GDD y agregará las fechas faltantes.
		Servicio 3: http://localhost:8081/challenges/v1/test: Este servicio sólo es de prueba en donde llama al servicio 2 y retorna un objeto con fecha desde y hasta, cantidad de fechas dentro de esos periodos y una lista de fechas.
	
	NOTA:
		* el proyecto solución tiene un archivo de configuración `application.yml`
		* por defecto correrá en en el puerto 8081

### Descripción de yml
	api:
	  periodo: http://127.0.0.1:8080/periodos/api  # propiedad que utiliza el servicio de solución al desafío para saber a que url del servicio GDD llamar
	info:
	  version: v1  # propiedad que indica la versión de servicio solución
	spring:
	  profiles:
	    active: development
	  jackson:
	    serialization:
	      write-dates-as-timestamps: false
	# Server configuration
	server:
	  port: 8081 # puerto en donde servirá el servicio de solución
	  tomcat:
	    max-threads: 3
	  servlet:
	    session:
	      timeout: 30
	  error:
	    include-stacktrace: always
