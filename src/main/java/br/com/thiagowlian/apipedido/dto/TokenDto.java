package br.com.thiagowlian.apipedido.dto;

import javax.validation.constraints.NotBlank;

public class TokenDto {
	
	@NotBlank
	private String token;
	@NotBlank
	private String tipo;

	public TokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
