package br.com.thiagowlian.apipedido.dto;

public class TokenDto {
	
	private String token;
	private String tipo;

	public TokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

}