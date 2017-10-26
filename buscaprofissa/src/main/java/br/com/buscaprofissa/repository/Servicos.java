package br.com.buscaprofissa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.buscaprofissa.model.Servico;

@Repository
public interface Servicos extends JpaRepository<Servico, Long> {

	public List<Servico> findByUsuarioId(Long id);
}
