package br.com.buscaprofissa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.filter.UsuarioFilter;
import br.com.buscaprofissa.security.UsuarioLogado;
import br.com.buscaprofissa.service.CadastroUsuarioService;


@Controller
public class LoginController {
	
	@Autowired
	private CadastroUsuarioService service;
	
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal UsuarioLogado user, UsuarioFilter usuarioFilter) {
		if (user != null) {
			Usuario usuario = user.getUsuario();
			service.ativar(user.getUsuario());
			return "redirect:/";
			
			
			
		}
		
		return "externas/Login";
	}
	
	
	@RequestMapping("/")
	public String inicial(){
		return "internas/Inicial";
	}
	

	
	


	
	

	
}
