package br.com.thiagowlian.apipedido.controller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioModel> usuarioLogin =  usuarioRepository.findByNome(username);
		if(usuarioLogin.isPresent()) {
			return usuarioLogin.get();
		}
		throw new UsernameNotFoundException("Usuário não encontrado!");
	}

}
