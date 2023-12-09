
# Backend Report App [En desarrollo]

El respectivo backend funciona en conjunto con el proyecto
[fronted-report-app](https://github.com/carlosalmanzab/fronted-report-app)

El backend del proyecto está desarrollado en Java y Spring Boot. Utiliza el patrón de arquitectura REST para exponer sus servicios.
Los servicios del backend se dividen en las siguientes categorías:

- **Servicios de autenticación:** Estos servicios se encargan de autenticar y autorizar a los usuarios.
- **Servicios de reportes:** Estos servicios se encargan de crear, actualizar y eliminar reportes de incidentes.
- **Servicios de notificaciones:** Estos servicios se encargan de enviar notificaciones a las autoridades locales sobre los incidentes reportados.

## Author

- [@carlosalmanzab](https://www.github.com/carlosalmanzab)

## Instalar backend

1. Clone el repositorio de GitHub:

```powershell
  git clone https://github.com/carlosalmanzab/backend-report-app.git
```

2. Cambie al directorio del proyecto:

```powershell
  cd backend-report-app
```

3. Ejecute los siguientes comandos para construir el backend:

```powershell
  mvn clean install
```

4. Arranque el backend con el siguiente comando:

```powershell
  java -jar target/backend-report-app.jar
```


##  Variables de Entorno

Para correr el proyecto necesita agregar las siguiente variables de entorno
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/[nombre-bd]?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=`contraseña`
firebase-configuration-file=`file-path`
```

## License

[MIT](https://choosealicense.com/licenses/mit/)


## Conocimientos recomendados

 - [Intro to WebSockets with Spring](https://www.baeldung.com/websockets-spring)
 - [Using Firebase Cloud Messaging in Spring Boot Applications](https://www.baeldung.com/spring-fcm)
 - [Spring Boot Security and JWT tutorial with example](https://www.bezkoder.com/spring-boot-security-jwt/)

