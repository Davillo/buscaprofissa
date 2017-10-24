package br.com.buscaprofissa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Embeddable
public class Endereco {
	
	
	@Column(name = "estado")
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;

	

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	
	
	
	
	
}
