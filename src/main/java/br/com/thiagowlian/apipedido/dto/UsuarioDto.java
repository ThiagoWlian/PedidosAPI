package br.com.thiagowlian.apipedido.dto;

import javax.validation.constraints.NotBlank;

public class UsuarioDto {
	@NotBlank
	private String nome;
	@NotBlank
	private String senha;
	
	public UsuarioDto(UsuarioForm usuario) {
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
