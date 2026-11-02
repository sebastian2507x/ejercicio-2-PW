package com.login.config;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.login.model.Rol;
import com.login.model.Usuario;
import com.login.repository.RolRepository;
import com.login.repository.UsuarioRepository;

/**
 * Crea los roles y los usuarios de prueba la primera vez que arranca el
 * proyecto (solo si las tablas están vacías). Así no hace falta ejecutar el
 * script SQL a mano.
 */
@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initData(RolRepository rolRepository, UsuarioRepository usuarioRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			for (String nombre : new String[] { "USER", "CREATOR", "EDITOR", "ADMIN" }) {
				if (rolRepository.findByNombre(nombre).isEmpty()) {
					rolRepository.save(new Rol(nombre));
				}
			}

			// usuario -> {contraseña, rol}
			Map<String, String[]> usuarios = Map.of(
					"admin", new String[] { "admin123", "ADMIN" },
					"creator", new String[] { "creator123", "CREATOR" },
					"editor", new String[] { "editor123", "EDITOR" },
					"user", new String[] { "user123", "USER" });

			usuarios.forEach((username, datos) -> {
				if (usuarioRepository.findByUsername(username).isEmpty()) {
					Usuario u = new Usuario();
					u.setUsername(username);
					u.setPassword(passwordEncoder.encode(datos[0]));
					u.setEnabled(true);
					u.getRoles().add(rolRepository.findByNombre(datos[1]).orElseThrow());
					usuarioRepository.save(u);
				}
			});
		};
	}
}
