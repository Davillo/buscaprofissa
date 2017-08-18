package br.com.buscaprofissa.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user){
		if(user != null){
			return "redirect:/teste";
		}
		return "Login";
	}
	
	@RequestMapping("/teste")
	public String teste(){
		return "usuario/Index";
	}
	
}
