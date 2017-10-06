package br.com.buscaprofissa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.buscaprofissa.model.Categoria;

public interface CategoriaRepository extends  JpaRepository<Categoria, Long> {
	public Optional<Categoria> findByAreaAtuacaoId(Long id); 
}
