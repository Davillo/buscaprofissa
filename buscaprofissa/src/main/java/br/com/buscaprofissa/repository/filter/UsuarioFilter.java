package br.com.buscaprofissa.repository.filter;

import br.com.buscaprofissa.model.AreaAtuacao;
import br.com.buscaprofissa.model.Categoria;
import br.com.buscaprofissa.model.Cidade;

public class UsuarioFilter {
	
	private Categoria categoria;
	private Cidade cidade;
	private String descricaoProfissional;
	private AreaAtuacao areaAtuacao;
	
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public String getDescricaoProfissional() {
		return descricaoProfissional;
	}
	public void setDescricaoProfissional(String descricaoProfissional) {
		this.descricaoProfissional = descricaoProfissional;
	}
	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}
	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
