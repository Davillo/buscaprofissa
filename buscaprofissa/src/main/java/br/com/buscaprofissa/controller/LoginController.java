package br.com.buscaprofissa.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.buscaprofissa.mail.Mailer;
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
	private Mailer mailer;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	public ModelAndView alterar(UsuarioFilter usuarioFilter,  Usuario usuario, BindingResult result,
			RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("externas/recuperarSenha");
			
		if(result.hasErrors()) {
			return recuperar(usuarioFilter, usuario);
		}
		
		
		Usuario user = usuarios.findByEmail(usuario.getEmail());
		
		if(user == null) {
			attributes.addFlashAttribute("mensagemErro", "Email não cadastrado!");
			return new ModelAndView("redirect:/recuperarSenha");
		}
		
			
			try {
				mailer.solicitarTrocaSenha(user);
				attributes.addFlashAttribute("mensagem", "Ok! Verifique o código de alteração no seu e-mail!");
				return new ModelAndView("redirect:/RecuperarSenhaCodigo");
			}catch (EmailNaoCadastradoException e) {
				result.rejectValue("email", e.getMessage(), e.getMessage());
				return recuperar(usuarioFilter, usuario);
				
			}
	
	}
	
	@RequestMapping("/RecuperarSenhaCodigo")
	public ModelAndView senhaCodigo(Usuario usuario,UsuarioFilter usuarioFilter) {
		ModelAndView mv = new ModelAndView("externas/RecuperarSenhaCodigo");
		return mv;
	}
	
	
	@PostMapping("/RecuperarSenhaCodigo")
	public ModelAndView alterarSenha(Usuario usuario,RedirectAttributes attributes, BindingResult result, UsuarioFilter usuarioFilter) {
		ModelAndView mv = new ModelAndView("externas/RecuperarSenhaCodigo");
		
		if(usuario.getSenha().length()<8){
			attributes.addFlashAttribute("mensagemErro", "A senha deve ter pelo menos 8 caracteres!");
			return new ModelAndView("redirect:/RecuperarSenhaCodigo");
		}
		Usuario user = usuarios.findByCodigo(usuario.getCodigo());
		
		if(user == null) {
			attributes.addFlashAttribute("mensagemErro", "Código inválido!");
			return new ModelAndView("redirect:/RecuperarSenhaCodigo");
		}
		
		String novaSenha = usuario.getSenha();
		
		user.setSenha(novaSenha);
		service.novaSenha(user);
		attributes.addFlashAttribute("mensagem", "Senha alterada com sucesso!");
		
		
		
		return new ModelAndView("redirect:/login");
		
		
		
	}
	
}
