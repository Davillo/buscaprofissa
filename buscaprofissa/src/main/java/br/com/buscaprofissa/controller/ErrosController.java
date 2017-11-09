package br.com.buscaprofissa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrosController {

	

	
	
	@GetMapping("/500")
	public String erroServidor(){
		return "500";
	}
	
}
