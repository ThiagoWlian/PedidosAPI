package br.com.thiagowlian.apipedido.controller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagowlian.apipedido.dto.PerfilForm;
import br.com.thiagowlian.apipedido.model.PerfilModel;
import br.com.thiagowlian.apipedido.repository.PerfilRepository;

@Service
public class PerfilService {
	@Autowired
	PerfilRepository perfilRepository;
	
	public void cadastrarPerfil(PerfilForm perfilForm) {
		PerfilModel perfilModel = perfilForm.converteParaPerfilModel();
		perfilRepository.save(perfilModel);
	}
	
	public Optional<PerfilModel> buscarProdutoPorId(int id) {
		
		Optional<PerfilModel> perfilRespostaQuery = perfilRepository.findById(id);
		return perfilRespostaQuery;
		
	}
}
