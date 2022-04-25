package br.com.thiagowlian.apipedido.dto;

import javax.validation.constraints.NotBlank;

import br.com.thiagowlian.apipedido.model.PerfilModel;

public class PerfilForm {
	@NotBlank
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public PerfilModel converteParaPerfilModel() {
		PerfilModel perfilResultante = new PerfilModel(this.nome);
		return perfilResultante;
	}
	
}
