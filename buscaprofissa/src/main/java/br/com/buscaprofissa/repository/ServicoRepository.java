package br.com.buscaprofissa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.buscaprofissa.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
