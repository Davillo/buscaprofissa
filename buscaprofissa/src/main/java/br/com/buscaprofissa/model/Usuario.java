package br.com.buscaprofissa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.thymeleaf.util.StringUtils;




@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotBlank(message = "O nome é obrigatório!")
	private String nome;
	
	//@NotBlank(message = "O sobrenome é obrigatório!")
	private String sobrenome;
	
	private String codigo;
	
	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	private String cpf;
	
	@Email(message = "Informe um e-mail válido")
	//@NotBlank(message = "O e-mail é obrigatório!")
	private String email;
	
	//@NotBlank(message = "A senha é obrigatória!")
	@Size(min = 8, message = "Tamanho mínimo da senha deve ser 8 caracteres")
	private String senha;
	
	@Transient
	private String confirmacaoSenha;
	
	
	private Long pontuacao;
	
	private String foto;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	@Column(name = "area_atuacao")
	private AreaAtuacao areaAtuacao;
	
	private String descricaoProfissional;

	@Column(name = "data_nascimento")
	private String dataNascimento; 
	
	private String telefone;
	
	@Transient
	private String dataServico;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	

	public String getDataServico() {
		return dataServico;
	}

	public void setDataServico(String dataServico) {
		this.dataServico = dataServico;
	}


	@Embedded
	private Endereco endereco;
	
	@Column(name = "content_type")
	private String contentType;
	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	//getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFotoOuMock(){																			
		return !StringUtils.isEmpty(foto) ? "https://s3-sa-east-1.amazonaws.com/awintegrador/"+"thumbnail."+foto : "https://s3-sa-east-1.amazonaws.com/awintegrador/thumbnail.usuario-mock.png";
	}
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
	public String getDescricaoProfissional() {
		return descricaoProfissional;
	}

	public void setDescricaoProfissional(String descricaoProfissional) {
		this.descricaoProfissional = descricaoProfissional;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	

	public Long getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Long pontuacao) {
		this.pontuacao = pontuacao;
	}

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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
