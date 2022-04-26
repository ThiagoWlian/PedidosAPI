package br.com.thiagowlian.apipedido.controller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.thiagowlian.apipedido.model.UsuarioModel;
import br.com.thiagowlian.apipedido.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TokenService tokenService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioModel> usuarioLogin =  usuarioRepository.findByNome(username);//revisar
		if(usuarioLogin.isPresent()) {
			return usuarioLogin.get();
		}
		throw new UsernameNotFoundException("Usuário não encontrado!");
	}
	
	public void autenticarClienteViaToken(String token) {
		int usuarioId = tokenService.getUserIdFromToken(token);
		UsuarioModel usuario = usuarioRepository.findById(usuarioId).get();//revisar
		UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario.getUsername(), null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(user);
	}

}
