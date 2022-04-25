package br.com.thiagowlian.apipedido.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.thiagowlian.apipedido.model.PerfilModel;
import br.com.thiagowlian.apipedido.model.UsuarioModel;

public class UsuarioConsultaPorIdDto {
	
	private String nome;
	private List<PerfilModel> perfis = new ArrayList<>(); 
	
	public UsuarioConsultaPorIdDto(UsuarioModel usuarioModel) {
		this.nome = usuarioModel.getUsername();
		this.perfis = usuarioModel.getPerfis();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PerfilModel> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilModel> perfis) {
		this.perfis = perfis;
	}
	
}
