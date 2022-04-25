package br.com.thiagowlian.apipedido.dto;

import br.com.thiagowlian.apipedido.model.PerfilModel;

public class PerfilDto {
	private String nome;
	
	public PerfilDto (PerfilModel perfilModel) {
		this.nome = perfilModel.getNome();
	}
	
	public PerfilDto(PerfilForm perfilForm) {
		this.nome = perfilForm.getNome();
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
