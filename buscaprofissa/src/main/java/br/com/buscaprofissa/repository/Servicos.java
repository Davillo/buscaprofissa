package br.com.buscaprofissa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.buscaprofissa.model.Servico;
import br.com.buscaprofissa.model.StatusSolicitacao;

@Repository
public interface Servicos extends JpaRepository<Servico, Long> {

	public Page<Servico> findByUsuarioIdAndStatusOrderByDataServico(Long id,StatusSolicitacao aceito,Pageable pageable);
	public List<Servico> findByUsuarioOrderByDataServico(Long id);
	public List<Servico> findByUsuarioIdAndStatusOrderByDataServico(Long id, StatusSolicitacao solicitacao);
}
