# **DCentral** - Gestor de colecciones

## Sobre el proyecto

**DCentral** es la idea que se ha planteado para presentar como proyecto de fin de ciclo de **Desarrollo de Aplicaciones Multiplatadorma** en el curso 2020-2021. Se ha desarrollado una aplicación de escritorio usando **JavaFX**, **Hibernate** y **MySQL** que gestiona múltiples colecciones.

## Software utilizado a la hora de desarrollar el proyecto

Para el desarrollo de este proyecto se ha usado **Eclipse** y **XAMPP**. Por lo tanto, será recomendable contar con estos u otros programas similares en el equipo para poder ejectuar correctamente el código que se encuentra en este repositorio.

[<img src="https://img.utdstc.com/icon/3c7/fcf/3c7fcf4930fa9402c22cee35e03fe9fcf9e8e47c9381d6b9e6922d71ee2e067a:200" alt="Eclipse" width="35"/>](https://www.eclipse.org/ide/)
[<img src="https://www.expertosdecomputadoras.com/wp-content/uploads/2012/02/como%20instalar%20xampp%20eaccelerator%20en%20un%20mac.png" alt="XAMPP" width="35" style="margin-left: 10px;"/>](https://www.apachefriends.org/es/index.html)

## Librerías usadas para la interfaz de usuario

- Para desarrollar toda la interfaz de usuario de la aplicación se ha usado **JavaFX**. Todos los estilos se han realizado a mano usando **CSS**.

## Cómo correr **DCentral**

En primer lugar será necesario ejecutar el script de la base de datos que se encuentra en la sección de documentación de este repositorio.

Una vez que se cuente con la base de datos de **DCentral** correctamente instalada en el equipo, será necesario abrir la carpeta que contiene el código con el **IDE Eclipse** (o similares).

En el caso de usar Eclipse, para poder ejecutar correctamente la aplicación, será necesario instalar **e(fx)clipse** además de tener un añadido al **VM arguments** dentro del **Run Configurations** de **Eclipse** el **SDK** de **JavaFX** (que se encuentra en la carpeta de **Documentación** de este repositorio) que se vaya a usar. La linea que ha sido agregada para el desarrollo de la aplicación ha sido la siguiente:

```bash
--module-path "D:\JAVA\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
```
Como es obvio, la ruta "D:\JAVA\javafx-sdk-15.0.1\lib" ha de ser modificada y ajustada a la ruta en la que se encuentre el **SDK** de **JavaFX** en el equipo que corra la aplicación. Aparte de lo anteriormente comentado, no es necesario instalar ningún paquete o librería más para poder ejecutar esta aplicación, ya que todo lo necesario se encuentra en el archivo **pom.xml** y, por lo tanto, se descargará sin necesidad de una acción externa.

Las credenciales con las que se puede acceder a la aplicación sin crear una nueva cuenta son las siguientes:

- **Usuario**: admin
  -  **Contraseña**: 1234
  - **Rol**: administrador

- **Usuario**: Diego
  - **Contraseña**: 1234
  - **Rol**: usuario estándar

- **Usuario**: JDoe
  - **Contraseña**: 1234
  - **Rol**: usuario estándar

## Autor

Este proyecto ha sido creado y desarrollado por **Diego García Miño**.
