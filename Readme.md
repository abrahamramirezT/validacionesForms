Descargar el proyecto desde el repositorio 

Descomprimir la carpeta donde contiene el back y front 

Identificar las carpetas de cada uno: 
* Back con nombre demo 
* Front con nombre PokemonDataValidation


Correr proyecto del back( Spring boot):

Descargar IntelliJ Idea y abrir en la aplicacion la carpeta llamada demo 
Esperar a que se descarguen las dependencias para poder correr el proyecto
Darle run al proyecto para poder crear nuestra base de datos 


Validacion de la creacion de la base de datos:
Debes de tener instalado MySQL Workbench 8.0
Entrar en la aplicacion para verificar que se haya creado la base de datos del back 
Seleccinar la conexion localhost en el puerto 3306
Realizar los siguientes querys para verificar que nuetra base de datos se creo :

*** use pokemondatavalidation;
*** describe pokemons;
*** describe types;

Antes de pasar al front debemos de realizar unos registros en nuestra tabla types para que puedan visualizarte en el back y sea funcional
Para realizar los registros en la tabla types, te muestro unos ejemplos:

************************************************************************************************
use pokemondatavalidation;
INSERT INTO types (description, name) VALUES ('el pokémon de tipo planta que tiene hojas afiladas y puede camuflarse en su entorno', 'planta');
INSERT INTO types (description, name) VALUES ('el pokémon de tipo fuego que puede crear llamas tan intensas que pueden derretir el acero', 'fuego');
INSERT INTO types (description, name) VALUES ('el pokémon de tipo eléctrico que genera electricidad almacenando energía solar', 'eléctrico');
INSERT INTO types (description, name) VALUES ('el pokémon de tipo volador que puede alcanzar altas velocidades y maniobrar ágilmente en el aire', 'volador');
INSERT INTO types (description, name) VALUES ('el pokémon de tipo psíquico que posee poderes mentales capaces de doblar la realidad a su voluntad', 'psíquico');

*************************************************************************************************

Correr el proyecto de Front (Vue2):

Descargar Visual Studio code 
Abrir en el la carpeta del front 
Abrir una terminal en la aplicación 
Antes de ejecutar lo siguientes comando asegurarse de que la terminal este en la carpeta del proyecto
Poner el comando npm install para obtener el Node modules faltante 
Correr el proyecto con el comando npm run dev 
Abrir la URL en el navegador
