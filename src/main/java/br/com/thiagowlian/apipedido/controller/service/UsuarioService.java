package br.com.thiagowlian.apipedido.controller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagowlian.apipedido.dto.UsuarioForm;
import br.com.thiagowlian.apipedido.model.UsuarioModel;
import br.com.thiagowlian.apipedido.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public void cadastrarUsuario(UsuarioForm usuario) {
		UsuarioModel usuarioModel = usuario.converterParaUsuarioModel();
		usuarioRepository.save(usuarioModel);
	}
	
	public Optional<UsuarioModel> buscarUsuarioPorId(int id) {
		Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(id);
		return usuarioModel;
	}

}
