package br.com.buscaprofissa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public final class TesteController {
	
	@RequestMapping("/cadastro")
	public String login(){
		return "cadastroInicial";
	}
	
	@RequestMapping("/teste")
	public String teste(){
		return "login2";
	}
}
