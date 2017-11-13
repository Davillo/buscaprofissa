package br.com.buscaprofissa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.buscaprofissa.repository.filter.UsuarioFilter;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public ModelAndView index(UsuarioFilter usuarioFilter , BindingResult result){
		ModelAndView mv = new ModelAndView("externas/Index");
		return mv;
		
	}
	
	@RequestMapping("/categorias")
	public ModelAndView categorias(UsuarioFilter usuarioFilter , BindingResult result){
		ModelAndView mv = new ModelAndView("externas/Categorias");
		return mv;
	}
	
	@RequestMapping("/sobre")
	public ModelAndView sobre(UsuarioFilter usuarioFilter , BindingResult result) {
		ModelAndView mv = new ModelAndView("externas/Sobre");
		return mv;
	}
	
	
	
	
	
	
}
