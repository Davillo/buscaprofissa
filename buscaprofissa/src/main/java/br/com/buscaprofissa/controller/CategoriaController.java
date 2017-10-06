package br.com.buscaprofissa.controller;


import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.buscaprofissa.model.Categoria;
import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.CategoriaRepository;
import br.com.buscaprofissa.security.UsuarioLogado;
import br.com.buscaprofissa.service.CadastroUsuarioService;
import groovy.lang.Binding;


@Controller
public class CategoriaController {
	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private CadastroUsuarioService service;
	
	
	@RequestMapping("/oferecerservico")
	public String oferecer(){
		return "internas/CadastrarServico";
	}
	
	@RequestMapping("/categorias/1")
	public ModelAndView categoriaSelecionada(Usuario usuario){
		List<Categoria> categorias = repository.findByAreaAtuacaoId(Long.valueOf(1));
		ModelAndView mv = new ModelAndView("internas/SelecionarCategorias");
		
		mv.addObject("categorias",categorias);
		
		return mv;
	}
	
	@PostMapping("/categorias/save")
	public ModelAndView salvarCategorias(Usuario usuario, @AuthenticationPrincipal UsuarioLogado user,RedirectAttributes attributes
			,BindingResult result
			){
		
		usuario = user.getUsuario();
		if(usuario.getId().equals(user.getUsuario().getId())){
			try{
				user.setUsuario(usuario);
				service.atualizar(usuario);
				System.out.println("OK");
			}catch (HibernateException e) {
				System.out.println("Ex: "+e.getMessage() + "" + e.getCause());
				 return new ModelAndView("redirect:/");
			}
		}
		
		
	
	
		
		 attributes.addFlashAttribute("mensagem" , "Salvo com sucesso!");
		 return new ModelAndView("redirect:/categorias/1");
	}
	
	
}
