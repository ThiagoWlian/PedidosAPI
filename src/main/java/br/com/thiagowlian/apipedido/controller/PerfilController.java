package br.com.thiagowlian.apipedido.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagowlian.apipedido.controller.service.PerfilService;
import br.com.thiagowlian.apipedido.dto.PerfilDto;
import br.com.thiagowlian.apipedido.dto.PerfilForm;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	PerfilService perfilService;
	
	@PostMapping
	public ResponseEntity<PerfilDto> cadastraPerfil(@RequestBody @Valid PerfilForm perfilForm){
		perfilService.cadastrarPerfil(perfilForm);
		PerfilDto perfilResponse = new PerfilDto(perfilForm);
		return ResponseEntity.created(null).body(perfilResponse);
	}
	
}
