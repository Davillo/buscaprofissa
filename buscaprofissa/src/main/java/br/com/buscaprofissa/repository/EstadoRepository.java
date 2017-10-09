package br.com.buscaprofissa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.buscaprofissa.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
