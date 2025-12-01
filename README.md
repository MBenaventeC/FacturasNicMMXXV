# FacturasNicMMXXV

Esta librería tiene como objetivo la creación de DTEs, su representación PDF y envío de los DTE de manera compatible
con los requisitos del SII.
Actualmente implementa solo Facturas Electrónicas Exentas, pero es extensible a cualquier tipo de DTE.

## Requisitos previos a la ejecución
Se recomienda ejecutar el proyecto en el IDE Intellij para mayor facilidad en la instalación
de dependencias y ejecución del proyecto.

El working directory debe estar ubicado en la raíz del proyecto, es decir, *.../FacturasNicMMXXV*.

### Instalación de dependencias y entorno de ejecución
En el proyecto se encuentran el archivo **pom.xml**: para la creación y firma de 
DTE's, y para realizar el envío de los documentos utilizando la API que dispone el SII.

Este archivo se encuentran en la ruta:
- Java/pom.xml

Se deben instalar con el gestor de dependencias Maven.

### Archivos requeridos
Para poder crear DTE's, se necesitan algunos archivos escenciales para la ejecución y se deben
ubicar en rutas específicas para no alterar el código fuente de la librería. A continuación se nombran
cuáles y dónde deberían de estar ubicados dentro del proyecto:

- Autorización otorgada por el SII: *Java/*
- Certificado digital para la emisión de DTE: *Java/*
- Archivo .txt que contiene la contraseña correspondiente al certificado digital: *Java/*
- JSON que contiene los datos de los DTE a generar y posteriormente enviar en un EnvioDTE: *Java/src/main/resources/*

Además, se debe modificar el archivo *Java/src/main/resources/rutEnvia.txt* con el rut del enviador 
de los DTE hacia el SII.


---Consideración con respecto a SiiEnvioApi---
El Envio de los archivos requiere de generar las clases de la carpeta Schemas, esto puede ejecutarse con el pom o con el comando: mvn clean generate-sources

Además notar que la función EnviaDocumento requiere de un certificado, contraseña, rut de la compañia y requiere utilizar clases internas de Java que ya con las versiones modernas de dejaron de ser usables publicamente, por lo cual en el pom fue creado un plugin que recibe los argumentos y libera las clases dando argumentos adicionales a Java para la ejecución. Esta ejecución del pom puede ser ejecutada con el comando de terminal: mvn exec:exec@run-envio

Advertencia: Hay que tener cuidado con la dirección base desde donde se realiza la ejecución pues puede dar problemas al no encontrar archivos dependendiendo de si se ejecuta con VisualStudioCode o IntelliJ, lamentablemente no alcanzamos a realizar pruebas con algún archivo que ejecute estas instrucciones sin depender del pom.

### Archivos de salida

Los archivos generados durante el proceso de emisión de DTE, tanto los DTE en sí ya firmados como los documentos
pdf por cada DTE con la estructura exigida por el SII, quedarán guardados en *out/*