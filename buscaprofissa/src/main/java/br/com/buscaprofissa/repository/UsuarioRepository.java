package br.com.buscaprofissa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.buscaprofissa.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
