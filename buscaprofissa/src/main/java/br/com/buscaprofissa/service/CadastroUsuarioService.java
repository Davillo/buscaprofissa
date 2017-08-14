package br.com.buscaprofissa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.UsuarioRepository;
import br.com.buscaprofissa.service.exception.EmailUsuarioJaCadastradoException;

@Service
public class CadastroUsuarioService {

		@Autowired
		private UsuarioRepository repository;
		
		@Transactional 
		public void salvar(Usuario usuario){
			Optional<Usuario> emailUsuarioJaExistent = repository.findByEmail(usuario.getEmail());
			if(emailUsuarioJaExistent.isPresent()){
				throw new EmailUsuarioJaCadastradoException("E-mail de usuário já cadastrado");
			}
			
			repository.save(usuario);
		}
}
