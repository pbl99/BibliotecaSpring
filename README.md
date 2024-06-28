# BibliotecaSpring

Aplicación de Sistema de Gestión de Bibliotecas

## Descripción

BibliotecaApp es una aplicación para la gestión de bibliotecas que permite a los administradores crear, actualizar y eliminar libros, así como a los usuarios buscar libros disponibles y cogerlos prestados. También incluye funcionalidad básica de autenticación y gestión de sesiones de usuarios.

## Funcionalidades

- **Autenticación de usuarios**: Registro, inicio de sesión y gestión de sesiones.
- **Área de usuarios**: Gestionar Datos del perfil de los usuarios y visualizar historial de préstamos.
- **Gestión de libros**: Crear, actualizar, borrar y listar libros.
- **Búsqueda de libros**: Búsqueda de libros por ISBN.
- **Sistema de libros**: Tomar prestados libros por cierto período de tiempo

## Tecnologías Utilizadas

- **Backend**: Spring Boot
- **Base de Datos**: MySql
- **Frontend**: Thymeleaf
- **Gestión de dependencias**: Maven
- **Estilos**: Bootstrap

## Requisitos

- Java 11 o superior
- Maven 3.6.3 o superior

## Instalación

1. **Clonar el repositorio:**
    ```bash
    git clone https://github.com/pbl99/BibliotecaSpring.git
    cd BibliotecaSpring
    ```

2. **Compilar el proyecto:**

    ```bash
    mvn clean install
    ```

3. **Ejecutar la aplicación:**

    ```bash
    mvn spring-boot:run
    ```

4. **Acceder a la aplicación:**

    Abre tu navegador web y navega a `http://localhost:8080`.

## Configuración

### Base de Datos

Por defecto, la aplicación usa una base de datos MySql. Puedes cambiar la configuración de la base de datos en el archivo `application.properties` ubicado en `src/main/resources/`.

Ejemplo de configuración para MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

## Contribuir
Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Fork el repositorio
2. Crea una nueva rama (git checkout -b feature/nueva-funcionalidad)
3. Realiza tus cambios y haz commit (git commit -am 'Añadir nueva funcionalidad')
4. Push a la rama (git push origin feature/nueva-funcionalidad)
5. Crea un Pull Request
   
## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
