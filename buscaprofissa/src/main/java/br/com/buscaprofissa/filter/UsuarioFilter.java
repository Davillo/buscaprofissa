package br.com.buscaprofissa.filter;

import br.com.buscaprofissa.model.AreaAtuacao;
import br.com.buscaprofissa.model.Categoria;
import br.com.buscaprofissa.model.Cidade;

public class UsuarioFilter {
	
	private Categoria categoria;
	private AreaAtuacao areaAtuacao;
	private Cidade cidade;
	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	
}
