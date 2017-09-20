package br.com.buscaprofissa.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	
	@GetMapping("/login")
	public ModelAndView login(@AuthenticationPrincipal User user) {
		ModelAndView mv = new ModelAndView("login/Login");
		
		if (user != null) {
			return new ModelAndView("redirect:/home");
		}
		
		return mv;
	}
	
	
	@RequestMapping("/home")
	public String home(){
		return "Home";
	}

	
	
	

	
}
