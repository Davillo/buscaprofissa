package br.com.buscaprofissa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.buscaprofissa.model.Servico;
import br.com.buscaprofissa.model.StatusSolicitacao;

@Repository
public interface Servicos extends JpaRepository<Servico, Long> {

	public List<Servico> findByUsuarioId(Long id);
	public List<Servico> findByUsuarioIdAndStatus(Long id,StatusSolicitacao aceito);
	public List<Servico> findByUsuarioOrderByDataServico(Long id);
}
