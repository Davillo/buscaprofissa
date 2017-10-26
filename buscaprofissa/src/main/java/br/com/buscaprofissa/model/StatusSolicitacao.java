package br.com.buscaprofissa.model;

public enum StatusSolicitacao {
	
	PENDENTE("Pendente"),
	ACEITO("Aceito"),
	RECUSADO("Recusado");
	
	
	private String descricao;
	
	
	StatusSolicitacao(String descricao){
		this.descricao = descricao;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
