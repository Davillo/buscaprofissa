package br.com.buscaprofissa.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.buscaprofissa.model.Usuario;

@Controller
public final class UsuarioController {
	
	@RequestMapping("/cadastro")
	public ModelAndView cadastro(Usuario usuario){
		ModelAndView mv = new ModelAndView("/usuario/Cadastro");
		return mv;
	}
	
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return cadastro(usuario);
		}
		
	
		return new ModelAndView("redirect:/cadastro");
		
	}
	
}
