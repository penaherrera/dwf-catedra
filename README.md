# DWF404: Proyecto de cátedra
### Descripción

Esta aplicación JSF presenta una gestión sobre la información de un equipo de fútbol, incluyendo el uso de JPA el cual será utilizado para la gestión de mapeo de objetos relacionales a tablas de base de datos. 
El proyecto fue construido en base a los requerimientos del proyecto de cátedra de la materia Desarrollo de Aplicaciones Web con Frameworks.

## Tecnologías Utilizadas
* Java: JDK 17 -> https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
* Gestor de dependencias: Maven
* Gestor de servicios: WampServer ó Xampp Control Panel
* Base de datos: MySQL
* Servidor de Aplicaciones: Payara 5
* Framework: Jakarta Server Faces (JSF) versión 10
* Persistencia de datos: Jakarta Persistence API (JPA)
* Entorno de ejecución: Jakarta Enterprise Edition (Java EE)

## Despliegue
*Parte1*

Abrir WampServer ó Xampp Control Panel (si se abre este último debe dar click al botón "Start" tanto en Apache como en MySQL)

En este ejemplo se utilizará Xampp, una vez activados los servicios, dar click al botón "Admin" de MySQL

![image](https://github.com/user-attachments/assets/7b4a6cf9-7819-4b07-af45-df3bbe1d689c)

Al abrirse el navegador nos dirigimos al apartado "SQL" o "Importar" y seleccionamos el script de la Base de Datos "club_management" el cual fue compartido en el punto número 5 del documento PDF entregado, al finalizar veremos lo siguiente:

![image](https://github.com/user-attachments/assets/67d104de-1bc8-457f-8659-073e58c3a1c6)

Luego nos dirigimos al apartado "Cuentas de usuarios" y damos click al botón "Agregar cuenta de usuario" y digitamos lo siguiente:

![image](https://github.com/user-attachments/assets/e30756b0-18bd-4610-8756-8983f3242131)

Al bajar hacemos click en el botón "otorgar todos los privilegios al nombre que contiene comodín (username\_%)".
Luego damos click en el botón "seleccionar todo" de Privilegios globales.
Y verificamos que este activado el "Require None" de SSL, al finalizar damos click en "Continuar".


*Parte 2*

Abrir el IDE de IntelliJ IDEA y dar click al botón "Open" para buscar el proyecto descargado.

Una vez abierto, nos dirigimos al apartado "File" y damos click en la opción "Project Structure"

![image](https://github.com/user-attachments/assets/298d28d3-0c0b-4a67-a2d8-195ddbab5352)

Seleccionaremos la opción (java version "17.0.11") luego dar click en el botón "Aplpy" y "Ok"

![image](https://github.com/user-attachments/assets/f0a32bf8-51ba-4afe-9892-edf5ecb0e98e)

Ahora nos dirigimos al apartado "Run/Debug Configurations" y dar click a la opción "Edit Configurations..."

![image](https://github.com/user-attachments/assets/a253a0d1-743f-4617-84d2-5f9f98d5cb4f)

Nos dirigimos a la sección "Deployment" y agregaremos el archivo "war" tanto en "Deploy at the server startup" como en "Before Launch"

![image](https://github.com/user-attachments/assets/7dfb3ae4-d632-4f12-823f-102aae6f9bc1)

El resultado al ejecutar el proyecto será un Error 500 ya que no se a agregado una "connection pool" en el servidor Payara.
Para crear una, nos dirigimos a la siguiente url: http://localhost:4848/common/index.jsf

*Parte 3*

Al abrir la página index del servidor Payara nos dirigimos a la sección "JDBC" al desplegarse damos click a la opción "JDBC Connection Pools" y seleccionamos "New"

![image](https://github.com/user-attachments/assets/bdbf12ba-fda2-4b15-b9dc-7c4b1622c9a9)

En la siguiente ventana procederá a agregar los siguientes campos:

![image](https://github.com/user-attachments/assets/fa4d7975-f704-4f1e-b79e-514b4717fdc2)

Luego en la siguiente ventana al bajar deberá eliminar todas las propiedades del apartado "Additional Properties" y deberá dar click al botón add para agregar las siguientes:

![image](https://github.com/user-attachments/assets/d26a3fd5-da40-46b4-9a7b-9f86647c3381)

Por último nos dirigimos a la sección "JDBC Resources" para poder agregar el recurso recién creado

![image](https://github.com/user-attachments/assets/d2fd9e31-d139-4e97-befa-1d8ca2ed1de4)

Dar click al apartado "jdbc/_default" y en la sección "Pool Name: " cambiar de "H2Pool" a "test" y guardar

![image](https://github.com/user-attachments/assets/65b68360-2d05-402e-9418-519000c53492)

Al finalizar todos los pasos deberá ejecutar el proyecto nuevamente y el resultado final será el siguiente:

![image](https://github.com/user-attachments/assets/d3e2b97e-92cb-4cfe-af46-3af5a2ee2f94)

## Descripcion de los Paquetes
### beans:
Contiene las clases beans de la aplicación, se encargan de gestionar la lógica de negocio y la interacción con la vista JSF.
### repository:
Contiene las clases JPA, las cuales se encargarán de interactuar con la base de datos. Estas clases contienen las opreaciones a realizar CRUD y consultas más específicas sobre las entidades.
### domain:
Contiene las entidades, que son las clases que representan los objetos persistentes en la base de datos.
### servicios:
Se agregará en la próxima entrega.

## Funcionalidades: 
* Gestión de información de equipo de fútbol: Administra la plantilla completa de jugadores y su técnico, la calendarización de partidos y la comunicación con los miembros del club para recibir información sobre los eventos deportivos.
* JPA: Utilizado para la gestión de mapeo de objetos relacionales a tablas de base de datos.

## Contribuidores:
* Carlos Francisco Peña Herrera [https://github.com/penaherrera]
* José Daniel Molina Rivera [https://github.com/Daniel2452]
* Oscar Vladimir Alarcón Mendoza [https://github.com/Alarcon07]
* Christian Daniel Valladares Quintanilla [https://github.com/CSGValladares]
* Nelson Otoniel Maldonado Manzanares [https://github.com/maldonado20]
* Diego Ismael Chávez Acevedo [https://github.com/DiegoIsmaelChavez]
