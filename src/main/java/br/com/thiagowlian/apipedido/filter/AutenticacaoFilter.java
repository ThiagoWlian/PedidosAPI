package br.com.thiagowlian.apipedido.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.thiagowlian.apipedido.controller.service.AutenticacaoService;
import br.com.thiagowlian.apipedido.controller.service.TokenService;

public class AutenticacaoFilter extends OncePerRequestFilter{

	TokenService tokenService;
	AutenticacaoService autenticacaoService;
	
	public AutenticacaoFilter(TokenService tokenService, AutenticacaoService autenticacaoService) {
		super();
		this.tokenService = tokenService;
		this.autenticacaoService = autenticacaoService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = tokenService.recuperarTokenDaRequisicao(request);
		Boolean tokenValido = tokenService.isTokenValid(token);
		if(tokenValido) {
			autenticacaoService.autenticarClienteViaToken(token);
		}
		filterChain.doFilter(request, response);	
	}

}
