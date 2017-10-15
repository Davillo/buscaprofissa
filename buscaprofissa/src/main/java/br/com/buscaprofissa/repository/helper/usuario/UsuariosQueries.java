package br.com.buscaprofissa.repository.helper.usuario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);
	
	
}
