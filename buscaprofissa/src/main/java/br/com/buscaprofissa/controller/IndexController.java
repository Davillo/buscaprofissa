package br.com.buscaprofissa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(){
		return "Index";
	}
	
	@RequestMapping("/categorias")
	public String categorias(){
		return "Categorias";
	}
}
