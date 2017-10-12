package br.com.buscaprofissa.repository.helper.usuario;

import java.util.List;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	
	public List<Usuario> filtrar(UsuarioFilter filtro);
	
	
}
