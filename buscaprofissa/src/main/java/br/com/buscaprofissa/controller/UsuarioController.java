package br.com.buscaprofissa.controller;

import java.security.Principal;
import java.util.jar.Attributes;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.buscaprofissa.model.Sexo;
import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.UsuarioRepository;
import br.com.buscaprofissa.security.UsuarioLogado;
import br.com.buscaprofissa.service.CadastroUsuarioService;
import br.com.buscaprofissa.service.exception.EmailUsuarioJaCadastradoException;
import br.com.buscaprofissa.service.exception.SenhaEConfirmacaoDiferentesException;

@Controller
public final class UsuarioController {
	
	
	
	@Autowired
	private CadastroUsuarioService service;
	
	@Autowired
	private UsuarioRepository repository;

	@RequestMapping("/cadastro")
	public ModelAndView cadastro(Usuario usuario){
		ModelAndView mv = new ModelAndView("externas/CadastroUsuario");
		
	
		return mv;
	}
	
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return cadastro(usuario);
		}
		
		
		
		try{
			
			service.salvar(usuario);
			
		}catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(),e.getMessage());
			return cadastro(usuario);
		}catch(SenhaEConfirmacaoDiferentesException e){
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return cadastro(usuario);
		}
		
		attributes.addFlashAttribute("mensagem","Salvo com sucesso!");
		return new ModelAndView("redirect:/cadastro");
		
	}
	
	@GetMapping("/meusdados")
	public ModelAndView meusdados(@RequestParam("id") Long id){
		Usuario usuario = repository.findOne(id);
		ModelAndView mv = new ModelAndView("internas/MeusDados");
		mv.addObject("usuario", usuario);
		mv.addObject("sexos", Sexo.values());
		return mv;
	}
	
	
	
	
	@RequestMapping("/teste")
	public String chat(){
		return "Chat";
	}
	
	@PostMapping("/meusdadosSave")
	public ModelAndView salvarDados(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
	
		if(result.hasErrors()){
			System.out.println(">> "+usuario.getNome());
			
		}
		
		try{
			service.atualizar(usuario);
		}catch (Exception e) {
				System.out.println(">>" +e.getMessage());
		}
		
		attributes.addFlashAttribute("mensagem", "Salvo com sucesso!");
		return new ModelAndView("redirect:/meusdados/?id="+usuario.getId());
	}
	
	
	
	
}
