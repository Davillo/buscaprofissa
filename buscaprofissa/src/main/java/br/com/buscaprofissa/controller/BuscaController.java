package br.com.buscaprofissa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuscaController {
	
	@RequestMapping("/buscar")
	public String viewBusca(){
		return "externas/BuscarServicos";
	}
	
}
