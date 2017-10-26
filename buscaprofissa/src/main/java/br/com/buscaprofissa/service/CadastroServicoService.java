package br.com.buscaprofissa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.buscaprofissa.model.Servico;
import br.com.buscaprofissa.repository.Servicos;

@Service
public class CadastroServicoService {
	
	@Autowired
	private Servicos servicos;
	
	@Transactional
	public void salvar(Servico servico) {
		servicos.save(servico);
	}
	
}
