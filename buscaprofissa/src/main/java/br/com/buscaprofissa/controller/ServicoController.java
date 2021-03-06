package br.com.buscaprofissa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.buscaprofissa.controller.page.PageWrapper;
import br.com.buscaprofissa.mail.Mailer;
import br.com.buscaprofissa.model.Servico;
import br.com.buscaprofissa.model.StatusSolicitacao;
import br.com.buscaprofissa.model.Usuario;
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
		mv.addObject("servicos",servicos.findByUsuarioIdAndStatusOrderById(usuario.getUsuario().getId(), StatusSolicitacao.PENDENTE));
		return mv;
	}
	
	@GetMapping("/solicitacoes/recusar/{id}")
	public ModelAndView recusar(@PathVariable("id") Long id,@AuthenticationPrincipal UsuarioLogado usuario
			, UsuarioFilter usuarioFilter,RedirectAttributes attributes) {
		Servico servico = servicos.findOne(id);
		service.recusar(servico);
		
		attributes.addFlashAttribute("mensagemRecusado","Serviço recusado!");
		mailer.emailRecusarServico(servico);
		return new ModelAndView("redirect:/solicitacoes");
	}
	
	
	
	
	
	@GetMapping("/solicitacoes/aceitar/{id}")
	public ModelAndView aceitar(@PathVariable("id") Long id,@AuthenticationPrincipal UsuarioLogado usuario
			, UsuarioFilter usuarioFilter, RedirectAttributes attributes) {
		Servico servico = servicos.findOne(id);
		service.aceitar(servico);
		
		attributes.addFlashAttribute("mensagemAceito","Serviço aceito!");
		mailer.emailAceitarServico(servico);
		return new ModelAndView("redirect:/solicitacoes");
	}
	
	
	
	@RequestMapping("/agenda")
	public ModelAndView agenda(@AuthenticationPrincipal UsuarioLogado usuario, UsuarioFilter usuarioFilter,
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest
			) {
		ModelAndView mv = new ModelAndView("internas/Agenda");
		PageWrapper<Servico> paginaWrapper = new PageWrapper<>(servicos.findByUsuarioIdAndStatusOrderById(usuario.getUsuario().getId(), StatusSolicitacao.ACEITO, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
		
	}
	
	
	
	
	
	
}
