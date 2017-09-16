package br.com.buscaprofissa.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user){
		
		if(user != null){
			return "redirect:/home";
		}
		return "login/Login";
	}
	
	
	

	
	
	

	
}
