package br.com.thiagowlian.apipedido.controller.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.thiagowlian.apipedido.model.UsuarioModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${pedido.jwt.secret}")
	private String secret;
	
	@Value("${pedido.jwt.expiration}")
	private String expirationTime;
	
	public String gerarToken(Authentication authentication) {
		UsuarioModel usuarioLogado = (UsuarioModel) authentication.getPrincipal();
		Date dataAtual = new Date();
		Date dateExpiration = new Date(dataAtual.getTime() + Long.parseLong(expirationTime));// Buscar uma alternativa
		return Jwts.builder()
				.setIssuer("API de Pedidos")
				.setSubject(Integer.toString(usuarioLogado.getId()))
				.setIssuedAt(dataAtual)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public String recuperarTokenDaRequisicao(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isBlank() || !token.startsWith("Bearer ")) {
			return null;
		}	
		return token.substring(7, token.length());	
	}
	
	public Boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public int getUserIdFromToken(String token) {
		int userId = Integer.parseInt(Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject());
		return userId;
	}

}
