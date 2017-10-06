package br.com.buscaprofissa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.buscaprofissa.model.Categoria;

public interface CategoriaRepository extends  JpaRepository<Categoria, Long> {
	public List<Categoria> findByAreaAtuacaoId(Long id); 
}
