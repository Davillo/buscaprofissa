package br.com.buscaprofissa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.UsuarioRepository;
import br.com.buscaprofissa.service.event.UsuarioSalvoEvent;
import br.com.buscaprofissa.service.exception.EmailUsuarioJaCadastradoException;
import br.com.buscaprofissa.service.exception.SenhaEConfirmacaoDiferentesException;

@Service
public class CadastroUsuarioService {

		@Autowired
		private UsuarioRepository repository;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@Autowired
		private ApplicationEventPublisher publisher;
		
	
		
		@Transactional 
		public void salvar(Usuario usuario){
			Optional<Usuario> emailUsuarioJaExistent = repository.findByEmailIgnoreCaseAndAtivoTrue(usuario.getEmail());
			if(emailUsuarioJaExistent.isPresent()){
				throw new EmailUsuarioJaCadastradoException("E-mail de usuário já cadastrado");
			}
			if(!(usuario.getSenha().equals(usuario.getConfirmacaoSenha()))   ){
				throw new SenhaEConfirmacaoDiferentesException("Senhas não conferem");
			}
			
			
			
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
			usuario.setAtivo(true);
		
			repository.save(usuario);
		}
		
		
		@Transactional
		public void atualizar(Usuario usuario){
			usuario.setAtivo(true);
			publisher.publishEvent(new UsuarioSalvoEvent(usuario));
			repository.saveAndFlush(usuario);
		}
		
		@Transactional
		public void desativar(Usuario usuario){
			usuario.setAtivo(false);
			repository.saveAndFlush(usuario);
		}
	
}
