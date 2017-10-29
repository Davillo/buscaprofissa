package br.com.buscaprofissa.controller;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.buscaprofissa.mail.Mailer;
import br.com.buscaprofissa.model.Servico;
import br.com.buscaprofissa.model.Sexo;
import br.com.buscaprofissa.model.StatusSolicitacao;
import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.AreasAtuacoes;
import br.com.buscaprofissa.repository.Categorias;
import br.com.buscaprofissa.repository.Cidades;
import br.com.buscaprofissa.repository.Estados;
import br.com.buscaprofissa.repository.Usuarios;
import br.com.buscaprofissa.repository.filter.UsuarioFilter;
import br.com.buscaprofissa.security.UsuarioLogado;
import br.com.buscaprofissa.service.CadastroServicoService;
import br.com.buscaprofissa.service.CadastroUsuarioService;
import br.com.buscaprofissa.service.exception.CpfInvalidoException;
import br.com.buscaprofissa.service.exception.DataInvalidaException;
import br.com.buscaprofissa.service.exception.EmailUsuarioJaCadastradoException;
import br.com.buscaprofissa.service.exception.ErroEnviandoEmailException;
import br.com.buscaprofissa.service.exception.SenhaEConfirmacaoDiferentesException;

@Controller
public final class UsuarioController {
	
	
	
	@Autowired
	private CadastroUsuarioService service;
	
	@Autowired
	private AreasAtuacoes areaRep;
	
	@Autowired
	private Categorias categoriaRep;
	
	@Autowired
	private Estados estadoRep;
	
	@Autowired
	private Cidades cidadeRep;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private CadastroServicoService servicos;

	@RequestMapping("/cadastro")
	public ModelAndView cadastro(Usuario usuario,UsuarioFilter usuarioFilter){
		ModelAndView mv = new ModelAndView("externas/CadastroUsuario");
		
	
		return mv;
	}
	
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes, UsuarioFilter usuarioFilter){
		if(result.hasErrors()){
			return cadastro(usuario,usuarioFilter);
		}
		
		
		
		try{
		
			service.salvar(usuario);
			
		}catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(),e.getMessage());
			return cadastro(usuario, usuarioFilter);
		}catch(SenhaEConfirmacaoDiferentesException e){
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return cadastro(usuario,usuarioFilter);
		}
		
		attributes.addFlashAttribute("mensagem","Salvo com sucesso!");
		return new ModelAndView("redirect:/cadastro");
		
	}

	
	@RequestMapping("/meusdados")
	public ModelAndView meusdados(@AuthenticationPrincipal UsuarioLogado usuario,
			UsuarioFilter usuarioFilter){
		Usuario user = usuario.getUsuario();
		ModelAndView mv = new ModelAndView("internas/MeusDados");
		mv.addObject("usuario", user);
		mv.addObject("areas", areaRep.findAll());
		mv.addObject("categorias", categoriaRep.findAll());
		
		mv.addObject("sexos", Sexo.values());
		mv.addObject("cidades",cidadeRep.findAll());
		mv.addObject("estados",estadoRep.findAll());
	
		return mv;
		
	}
	

	@PostMapping("/meusdadosSave")
	public ModelAndView salvarDados(@AuthenticationPrincipal UsuarioLogado user,Usuario  usuario, BindingResult result,UsuarioFilter usuarioFilter ,RedirectAttributes attributes){

		if(result.hasErrors()){
			return new ModelAndView("redirect:/meusdados");
			
		}
		
		try{
			service.atualizar(usuario);
			user.setUsuario(usuario);
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso!");
		}catch (DataInvalidaException e) {
			result.rejectValue("dataNascimento", e.getMessage(),e.getMessage());
			return meusdados( user, usuarioFilter);
		}catch(CpfInvalidoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
		}
		
		
		return new ModelAndView("redirect:/meusdados");
	}
	
	
	@GetMapping("/meusdados/desativar")
	public ModelAndView desativar(@AuthenticationPrincipal UsuarioLogado user,Usuario usuario){
		usuario = user.getUsuario();

		try{
			service.desativar(usuario);
			user.setUsuario(usuario);
			
		}catch (Exception e) {
			System.out.println(">>" +e.getMessage() + ">> "+e.getCause());
		}
		
		return new ModelAndView("redirect:/logout");
		
	}
	
	@GetMapping("/verPerfil/{id}")
	public ModelAndView visualizarPerfil(@PathVariable("id") Long id, UsuarioFilter usuarioFilter) {
		Usuario usuario = usuarios.findOne(id);
		ModelAndView mv = new ModelAndView("internas/VisualizarPerfil");
		
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	
	
	@PostMapping("/verPerfil/{id}")
	public ModelAndView solicitar( @PathVariable("id") Long id,@AuthenticationPrincipal UsuarioLogado usuarioLogado,
			 RedirectAttributes attributes, Usuario user
			) {
		
		ModelAndView mv = new ModelAndView("internas/VisualizarPerfil");
		Usuario usuario = usuarios.findOne(id);
		mv.addObject("usuario", usuario);
		Servico servico = new Servico();
		servico.setDataServico(user.getDataServico());
		servico.setNomeCliente(usuarioLogado.getUsuario().getNome());
		servico.setEmailCliente(usuarioLogado.getUsuario().getEmail());
		servico.setStatus(StatusSolicitacao.PENDENTE);
		servico.setUsuario(usuario);
		
		
		try {
			servicos.salvar(servico);
			mailer.enviar(usuario, usuarioLogado);
			attributes.addFlashAttribute("mensagem", "Solicitação enviada via e-mail!");
			
		
		}catch (ErroEnviandoEmailException e) {
			
		}
		
		 return new ModelAndView("redirect:/verPerfil/{id}");
		
		
	}
		
	
}
