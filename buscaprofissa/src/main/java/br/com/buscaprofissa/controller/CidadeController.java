package br.com.buscaprofissa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.buscaprofissa.model.Cidade;
import br.com.buscaprofissa.repository.Cidades;


@Controller
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private Cidades cidades;
	
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorIdEstado(
			@RequestParam(name = "estado",defaultValue = "-1") Long idEstado){
		return cidades.findByEstadoId(idEstado);
	}
}
