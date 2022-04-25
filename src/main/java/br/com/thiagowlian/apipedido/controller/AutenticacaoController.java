package br.com.thiagowlian.apipedido.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagowlian.apipedido.controller.service.TokenService;
import br.com.thiagowlian.apipedido.dto.LoginForm;
import br.com.thiagowlian.apipedido.dto.TokenDto;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authenticationMagnager;
	
	@Autowired
	private TokenService tokenService;
	
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm loginForm){
		UsernamePasswordAuthenticationToken loginDados = loginForm.converterParaUsernamePasswordAuthenticationToken();
		
		try {
			 Authentication authentication = authenticationMagnager.authenticate(loginDados);
			 String token  = tokenService.gerarToken(authentication);
			 return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
