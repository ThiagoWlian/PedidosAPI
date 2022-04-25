package br.com.thiagowlian.apipedido.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USUARIO")
public class UsuarioModel implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String nome;
	private String senha;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "PERFIL_USUARIO",
			joinColumns = {@JoinColumn(name = "PERFIL_ID")},
			inverseJoinColumns = {@JoinColumn(name = "USUARIO_ID")}
	)
	private List<PerfilModel> perfis = new ArrayList<>(); 
	
	public UsuarioModel() {}

	public UsuarioModel(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<PerfilModel> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilModel> perfis) {
		this.perfis = perfis;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return nome;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
