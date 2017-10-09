package br.com.buscaprofissa.service.event;

import org.thymeleaf.util.StringUtils;

import br.com.buscaprofissa.model.Usuario;

public class UsuarioSalvoEvent {
	
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	

	public UsuarioSalvoEvent(Usuario usuario) {
		this.usuario = usuario;
	} 
	
	public boolean temFoto(){
		return !StringUtils.isEmpty(usuario.getFoto());
	}
	
	
	
}
