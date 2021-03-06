package br.com.buscaprofissa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.helper.usuario.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	public Optional<Usuario> findByEmailIgnoreCase(String email);
	public Usuario findByEmail(String email);
	public Usuario findByCodigo(String codigo);
	
}
