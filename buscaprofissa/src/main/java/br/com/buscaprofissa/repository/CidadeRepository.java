package br.com.buscaprofissa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.buscaprofissa.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
