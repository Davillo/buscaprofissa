package br.com.buscaprofissa.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.buscaprofissa.filter.UsuarioFilter;
import br.com.buscaprofissa.model.Usuario;


public interface UsuariosQueries {
		
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);	
}
