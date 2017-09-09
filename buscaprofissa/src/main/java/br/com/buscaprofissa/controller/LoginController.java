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
			return home();
		}
		return "Login";
	}
	
	
	@RequestMapping("/home")
	public String home(){
		return "Home";
	}
	

	
}