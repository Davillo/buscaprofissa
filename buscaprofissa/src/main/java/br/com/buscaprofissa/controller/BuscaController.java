package br.com.buscaprofissa.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.buscaprofissa.controller.page.PageWrapper;
import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.AreasAtuacoes;
import br.com.buscaprofissa.repository.Categorias;
import br.com.buscaprofissa.repository.Cidades;
import br.com.buscaprofissa.repository.Usuarios;
import br.com.buscaprofissa.repository.filter.UsuarioFilter;

@Controller
public class BuscaController {
	
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private AreasAtuacoes areas;
	@Autowired
	private Categorias categorias;
	@Autowired
	private Cidades cidades;
	
	
	@RequestMapping("/buscar")
	public ModelAndView viewBusca(UsuarioFilter usuarioFilter,BindingResult result
			,@PageableDefault(size=2) Pageable pageable, HttpServletRequest httpServletRequest){
		
		ModelAndView mv = new ModelAndView("externas/BuscarServicos");
		
		mv.addObject("areas", areas.findAll());
		mv.addObject("categorias", categorias.findAll());
		mv.addObject("cidades", cidades.findAll());
		
		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(usuarios.filtrar(usuarioFilter, pageable),  httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	
	
	
	
}
