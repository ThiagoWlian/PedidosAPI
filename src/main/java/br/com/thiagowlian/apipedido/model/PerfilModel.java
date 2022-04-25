package br.com.thiagowlian.apipedido.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "PERFIL")
public class PerfilModel implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String nome;
	
	public PerfilModel() {}
	
	public PerfilModel(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return nome;
	}

}
