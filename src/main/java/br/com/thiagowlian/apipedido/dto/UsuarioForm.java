package br.com.thiagowlian.apipedido.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.thiagowlian.apipedido.model.UsuarioModel;

public class UsuarioForm {
	@NotBlank
	private String nome;
	@NotBlank
	private String senha;
	
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
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}
	
	public UsuarioModel converterParaUsuarioModel() {
		return new UsuarioModel(this.nome,this.senha);
	}
	
}
