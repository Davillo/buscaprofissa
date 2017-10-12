package br.com.buscaprofissa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView viewBusca(UsuarioFilter usuarioFilter){
		
		ModelAndView mv = new ModelAndView("externas/BuscarServicos");
		mv.addObject("usuarios", usuarios.filtrar(usuarioFilter));
		mv.addObject("areas", areas.findAll());
		mv.addObject("categorias", categorias.findAll());
		mv.addObject("cidades", cidades.findAll());
		return mv;
	}
	
	
	
}
