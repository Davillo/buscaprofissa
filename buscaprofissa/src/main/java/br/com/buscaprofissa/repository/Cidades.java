package br.com.buscaprofissa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.buscaprofissa.model.Cidade;

public interface Cidades extends JpaRepository<Cidade, Long> {
	
	
	public List<Cidade> findByEstadoId(Long idEstado);
}
