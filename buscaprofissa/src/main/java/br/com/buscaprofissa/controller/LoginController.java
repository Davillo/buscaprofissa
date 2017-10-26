package br.com.buscaprofissa.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.Usuarios;
import br.com.buscaprofissa.repository.filter.UsuarioFilter;
import br.com.buscaprofissa.security.UsuarioLogado;
import br.com.buscaprofissa.service.CadastroUsuarioService;
import br.com.buscaprofissa.service.exception.EmailNaoCadastradoException;


@Controller
public class LoginController {
	
	@Autowired
	private CadastroUsuarioService service;
	
	@Autowired
	private Usuarios usuarios;
	
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal UsuarioLogado user, UsuarioFilter usuarioFilter) {
		if (user != null) {
		
			service.ativar(user.getUsuario());
			return "redirect:/";
			
			
			
		}
		
		return "externas/Login";
	}
	
	
	@RequestMapping("/")
	public ModelAndView inicial(UsuarioFilter usuarioFilter){
		ModelAndView mv = new ModelAndView("internas/Inicial");
		return mv;
	}
	
	
	
	@RequestMapping("/recuperarSenha")
	public ModelAndView recuperar(UsuarioFilter usuarioFilter, Usuario usuario) {
		ModelAndView mv = new ModelAndView("externas/recuperarSenha");
		return mv;
	}
	

	@PostMapping("/recuperarSenha")
	public ModelAndView alterar(UsuarioFilter usuarioFilter, @Valid Usuario usuario, BindingResult result,
			RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("externas/recuperarSenha");
			
		if(result.hasErrors()) {
			return recuperar(usuarioFilter, usuario);
		}
		
		Usuario user = usuarios.findByEmailAndCpf(usuario.getEmail(), usuario.getCpf());
		if(user == null) {
			attributes.addFlashAttribute("mensagem", "Email ou CPF não cadastrados!");
			return new ModelAndView("redirect:/recuperarSenha");
		}
		
			
			try {
				String novaSenha = usuario.getSenha();
				usuario = user;
				usuario.setSenha(novaSenha);
				service.novaSenha(usuario);
				attributes.addFlashAttribute("mensagem", "Senha alterada com sucesso!");
				return new ModelAndView("redirect:/recuperarSenha");
			}catch (EmailNaoCadastradoException e) {
				result.rejectValue("email", e.getMessage(), e.getMessage());
				return recuperar(usuarioFilter, usuario);
				
			}
	
	}
	
}
