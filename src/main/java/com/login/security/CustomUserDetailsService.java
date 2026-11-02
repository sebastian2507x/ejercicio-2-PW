package com.login.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login.model.Usuario;
import com.login.repository.UsuarioRepository;

/** Lee el usuario y sus roles desde MySQL (en vez del InMemoryUserDetailsManager). */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.<GrantedAuthority>map(rol -> new SimpleGrantedAuthority(rol.getNombre())).toList();

		return org.springframework.security.core.userdetails.User.withUsername(usuario.getUsername())
				.password(usuario.getPassword()).disabled(!usuario.isEnabled()).authorities(authorities).build();
	}
}
