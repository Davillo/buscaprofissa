package br.com.buscaprofissa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.buscaprofissa.repository.Servicos;
import br.com.buscaprofissa.repository.filter.UsuarioFilter;
import br.com.buscaprofissa.security.UsuarioLogado;

@Controller
public class ServicoController {
	
	@Autowired
	private Servicos servicos;

	@RequestMapping("/solicitacoes")
	public ModelAndView solicitacoes(@AuthenticationPrincipal UsuarioLogado usuario
			, UsuarioFilter usuarioFilter) {
		ModelAndView mv = new ModelAndView("internas/Solicitacoes");
		mv.addObject("servicos",servicos.findByUsuarioId(usuario.getUsuario().getId()));
		return mv;
	}
	
	
	
}
