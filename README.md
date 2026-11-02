# login

CRUD de productos con inicio de sesión y permisos por rol, leyendo usuarios y roles desde MySQL.
Spring Boot 3.5.6 · Java 21 · Spring Security · Spring Data JPA · JSP · MySQL.

## Requisitos
- JDK 21
- MySQL en `localhost:3306` (en mi caso use MySQL Workbench)

## Cómo ejecutarlo
1. Abre `src/main/resources/application.properties` y cambia `spring.datasource.username` y `spring.datasource.password` por los de tu MySQL.
2. Base de datos (elige una opción):
   - **Automática:** no hagas nada. Al arrancar se crea la base `securitydb`, las tablas y los usuarios de prueba.
   - **Manual:** ejecuta `database/script.sql` en Workbench antes de arrancar.
3. Arranca el proyecto:
   - Windows: `mvnw.cmd spring-boot:run`
   - Linux/Mac: `./mvnw spring-boot:run`
4. Abre http://localhost:8080 e inicia sesión.

## Usuarios de prueba
| Usuario | Contraseña | Rol |
|---|---|---|
| admin | admin123 | ADMIN |
| creator | creator123 | CREATOR |
| editor | editor123 | EDITOR |
| user | user123 | USER |

## Permisos por rol
| Ruta | Acción | Roles permitidos |
|---|---|---|
| `/` | Listar productos | USER, CREATOR, EDITOR, ADMIN |
| `/new` (`/save`) | Crear producto | ADMIN, CREATOR |
| `/edit/**` (`/update/**`) | Editar producto | ADMIN, EDITOR |
| `/delete/**` | Eliminar producto | ADMIN |

Si un usuario entra a una ruta para la que no tiene permiso, se muestra la página personalizada `/403`.

## Base de datos
Tablas: `users` (user_id, username, password, enabled), `roles` (role_id, name) y `users_roles` (user_id, role_id).
Las contraseñas se guardan encriptadas con BCrypt.
