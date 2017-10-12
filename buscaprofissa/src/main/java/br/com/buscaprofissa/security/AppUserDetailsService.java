package br.com.buscaprofissa.security;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.Usuarios;


@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private Usuarios usuarios;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 
		Optional<Usuario> usuarioOptional = usuarios.findByEmailIgnoreCaseAndAtivoTrue(email);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos ou conta inativa"));
		return new UsuarioLogado(usuario, new HashSet<>());
	}
	
	
	


}
