package br.com.buscaprofissa.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.buscaprofissa.model.AreaAtuacao;
import br.com.buscaprofissa.model.Sexo;
import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.AreaAtuacaoRepository;
import br.com.buscaprofissa.repository.CategoriaRepository;
import br.com.buscaprofissa.repository.CidadeRepository;
import br.com.buscaprofissa.repository.EstadoRepository;
import br.com.buscaprofissa.repository.UsuarioRepository;
import br.com.buscaprofissa.security.UsuarioLogado;
import br.com.buscaprofissa.service.CadastroUsuarioService;
import br.com.buscaprofissa.service.exception.EmailUsuarioJaCadastradoException;
import br.com.buscaprofissa.service.exception.SenhaEConfirmacaoDiferentesException;

@Controller
public final class UsuarioController {
	
	
	
	@Autowired
	private CadastroUsuarioService service;
	
	@Autowired
	private AreaAtuacaoRepository areaRep;
	
	@Autowired
	private CategoriaRepository categoriaRep;
	
	@Autowired
	private EstadoRepository estadoRep;
	
	@Autowired
	private CidadeRepository cidadeRep;

	@RequestMapping("/cadastro")
	public ModelAndView cadastro(Usuario usuario){
		ModelAndView mv = new ModelAndView("externas/CadastroUsuario");
		
	
		return mv;
	}
	
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return cadastro(usuario);
		}
		
		
		
		try{
			
			service.salvar(usuario);
			
		}catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(),e.getMessage());
			return cadastro(usuario);
		}catch(SenhaEConfirmacaoDiferentesException e){
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return cadastro(usuario);
		}
		
		attributes.addFlashAttribute("mensagem","Salvo com sucesso!");
		return new ModelAndView("redirect:/cadastro");
		
	}

	
	@RequestMapping("/meusdados")
	public ModelAndView meusdados(@AuthenticationPrincipal UsuarioLogado usuario){
		Usuario user = usuario.getUsuario();
		ModelAndView mv = new ModelAndView("internas/MeusDados");
		
		mv.addObject("areas", areaRep.findAll());
		mv.addObject("categorias", categoriaRep.findAll());
		mv.addObject("usuario", user);
		mv.addObject("sexos", Sexo.values());
		mv.addObject("cidades",cidadeRep.findAll());
		mv.addObject("estados",estadoRep.findAll());
		
		
		
		
		return mv;
		
	}
	
	
	
	
	@RequestMapping("/teste")
	public String chat(){
		return "Chat";
	}
	
	@PostMapping("/meusdadosSave")
	public ModelAndView salvarDados(@AuthenticationPrincipal UsuarioLogado user,Usuario  usuario, BindingResult result, RedirectAttributes attributes){

		if(result.hasErrors()){
			return new ModelAndView("redirect:/meusdados");
			
		}
		
		try{
			service.atualizar(usuario);
			user.setUsuario(usuario);
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso!");
		}catch (Exception e) {
				System.out.println(">>" +e.getMessage() + ">> "+e.getCause());
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
	
	
	
	
}
