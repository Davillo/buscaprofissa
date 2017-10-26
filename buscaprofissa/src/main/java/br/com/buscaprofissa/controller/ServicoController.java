package br.com.buscaprofissa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.buscaprofissa.mail.Mailer;
import br.com.buscaprofissa.model.Servico;
import br.com.buscaprofissa.repository.Servicos;
import br.com.buscaprofissa.repository.filter.UsuarioFilter;
import br.com.buscaprofissa.security.UsuarioLogado;
import br.com.buscaprofissa.service.CadastroServicoService;

@Controller
public class ServicoController {
	
	@Autowired
	private Servicos servicos;
	
	@Autowired
	private CadastroServicoService service;

	@Autowired
	private Mailer mailer;
	
	@RequestMapping("/solicitacoes")
	public ModelAndView solicitacoes(@AuthenticationPrincipal UsuarioLogado usuario
			, UsuarioFilter usuarioFilter) {
		ModelAndView mv = new ModelAndView("internas/Solicitacoes");
		mv.addObject("servicos",servicos.findByUsuarioId(usuario.getUsuario().getId()));
		return mv;
	}
	
	@GetMapping("/solicitacoes/recusar/{id}")
	public ModelAndView recusar(@PathVariable("id") Long id,@AuthenticationPrincipal UsuarioLogado usuario
			, UsuarioFilter usuarioFilter) {
		Servico servico = servicos.findOne(id);
		service.recusar(servico);
		
	//	mailer.enviar(servico);
		return new ModelAndView("redirect:/solicitacoes");
	}
	
	
	@GetMapping("/solicitacoes/aceitar/{id}")
	public ModelAndView aceitar(@PathVariable("id") Long id,@AuthenticationPrincipal UsuarioLogado usuario
			, UsuarioFilter usuarioFilter) {
		Servico servico = servicos.findOne(id);
		service.aceitar(servico);
		
		//mailer.enviar(servico);
		return new ModelAndView("redirect:/solicitacoes");
	}
	
	
	
	
	
	
}
