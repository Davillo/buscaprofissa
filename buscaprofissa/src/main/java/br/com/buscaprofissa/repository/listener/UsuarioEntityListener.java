package br.com.buscaprofissa.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.security.UsuarioLogado;
import br.com.buscaprofissa.storage.FotoStorage;

public class UsuarioEntityListener {

	/*
	@Autowired
	private FotoStorage fotoStorage;

	@PostLoad
	public void postLoad(final Usuario usuario) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		usuario.setUrlFoto(fotoStorage.getUrl(usuario.getFotoOuMock()));
		usuario.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + usuario.getFotoOuMock()));

	}*/

}
