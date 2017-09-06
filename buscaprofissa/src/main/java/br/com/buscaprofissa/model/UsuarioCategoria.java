package br.com.buscaprofissa.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_categoria")
public class UsuarioCategoria implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public UsuarioCategoriaId getId() {
		return id;
	}

	public void setId(UsuarioCategoriaId id) {
		this.id = id;
	}

	@EmbeddedId
	private UsuarioCategoriaId id;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioCategoria other = (UsuarioCategoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
}
