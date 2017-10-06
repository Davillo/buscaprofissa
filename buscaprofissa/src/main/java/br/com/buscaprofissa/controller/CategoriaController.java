package br.com.buscaprofissa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.buscaprofissa.model.Categoria;
import br.com.buscaprofissa.repository.CategoriaRepository;

@Controller
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@RequestMapping("/oferecerservico")
	public String oferecer(){
		return "internas/CadastrarServico";
	}
	
	
	
	@RequestMapping("/assistenciatecnica")
	public ModelAndView assistencia(){
		
		ModelAndView mv = new ModelAndView("internas/SelecionarCategorias");
		Long id =  (long) 1;
		Optional<Categoria> categorias = repository.findByAreaAtuacaoId(id);
		
		System.out.println(">>>"+categorias.toString());
		return mv;
	}
}
