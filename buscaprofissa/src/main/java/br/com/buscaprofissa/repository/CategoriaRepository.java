package br.com.buscaprofissa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.buscaprofissa.model.Categoria;

public interface CategoriaRepository extends  JpaRepository<Categoria, Long> {

}
