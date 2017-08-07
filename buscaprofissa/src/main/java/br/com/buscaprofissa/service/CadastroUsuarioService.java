package br.com.buscaprofissa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

		@Autowired
		private UsuarioRepository repository;
		
		@Transactional 
		public void salvar(Usuario usuario){
			repository.save(usuario);
		}
}
