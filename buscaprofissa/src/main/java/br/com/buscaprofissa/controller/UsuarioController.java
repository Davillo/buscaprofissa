package br.com.buscaprofissa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.service.CadastroUsuarioService;
import br.com.buscaprofissa.service.exception.EmailUsuarioJaCadastradoException;

@Controller
public final class UsuarioController {
	
	@Autowired
	private CadastroUsuarioService service;
	


	@RequestMapping("/cadastro")
	public ModelAndView cadastro(Usuario usuario){
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		
	
	
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
		}
		
		attributes.addFlashAttribute("mensagem","Salvo com sucesso!");
		return new ModelAndView("redirect:/cadastro");
		
	}
	
	@RequestMapping("/meusdados")
	public ModelAndView meusdados(Usuario usuario){
		ModelAndView mv = new ModelAndView("internas/MeusDados");
		return mv;
	}
	
	
	@RequestMapping("/teste")
	public String chat(){
		return "Chat";
	}
	
	@PostMapping("/meusdados")
	public ModelAndView salvarDados(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
		
		if(result.hasErrors()){
			return meusdados(usuario);
		}
		
		return new ModelAndView("redirect:/meusdados");
	}
	
	
	
}
