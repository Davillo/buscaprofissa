package br.com.buscaprofissa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.UsuarioRepository;
import br.com.buscaprofissa.service.exception.EmailUsuarioJaCadastradoException;
import br.com.buscaprofissa.service.exception.SenhaEConfirmacaoDiferentesException;

@Service
public class CadastroUsuarioService {

		@Autowired
		private UsuarioRepository repository;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
	
		
		@Transactional 
		public void salvar(Usuario usuario){
			Optional<Usuario> emailUsuarioJaExistent = repository.findByEmail(usuario.getEmail());
			if(emailUsuarioJaExistent.isPresent()){
				throw new EmailUsuarioJaCadastradoException("E-mail de usuário já cadastrado");
			}
			if(!(usuario.getSenha().equals(usuario.getConfirmacaoSenha()))   ){
				throw new SenhaEConfirmacaoDiferentesException("Senhas não conferem");
			}
			
			
			
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
			repository.save(usuario);
		}
		
		
		@Transactional
		public void atualizar(Usuario usuario){
			repository.saveAndFlush(usuario);
		}
		
	
}
