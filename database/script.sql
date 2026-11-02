-- Base de datos del proyecto (MySQL). Ejecutar una sola vez en MySQL Workbench.

CREATE DATABASE IF NOT EXISTS securitydb;
USE securitydb;

CREATE TABLE IF NOT EXISTS users (
  user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(64) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS roles (
  role_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users(user_id),
  FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

INSERT INTO roles (name) VALUES ('USER'), ('CREATOR'), ('EDITOR'), ('ADMIN');

-- Contraseñas encriptadas con BCrypt (admin123, creator123, editor123, user123)
INSERT INTO users (username, password, enabled) VALUES
('admin',   '$2b$10$e732LSkT8F..BuIuUtq/lORe9NLJnbnGbGfeMykg/8/iTHRFWgrXy', 1),
('creator', '$2b$10$ecY6WFoDY18qkeTe7Q/EXuApjcNSpeSPbWa11ADNy9vIEcT8BgIlq', 1),
('editor',  '$2b$10$AxBujvQW1Tb2GFaCFvu9zuw9IfnKbpteZXJw7bGwdORptLmwTXM0S', 1),
('user',    '$2b$10$bjjmoZkc5PvpDWUDM/.rnOLqyny5Vt5ts0M/pXy14Ly6.oYeJ1Z1.', 1);

INSERT INTO users_roles (user_id, role_id)
SELECT u.user_id, r.role_id FROM users u JOIN roles r
  ON (u.username = 'admin'   AND r.name = 'ADMIN')
  OR (u.username = 'creator' AND r.name = 'CREATOR')
  OR (u.username = 'editor'  AND r.name = 'EDITOR')
  OR (u.username = 'user'    AND r.name = 'USER');
