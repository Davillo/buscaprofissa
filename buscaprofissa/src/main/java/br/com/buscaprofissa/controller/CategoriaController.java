package br.com.buscaprofissa.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.buscaprofissa.model.Categoria;
import br.com.buscaprofissa.model.Cidade;
import br.com.buscaprofissa.repository.Categorias;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private Categorias categorias;
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Categoria> pesquisarPorIdCategoria(@RequestParam(name = "area",defaultValue = "-1") Long idArea){
		return categorias.findByAreaAtuacaoId(idArea);
	}
	
}
