package br.com.thiagowlian.apipedido.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagowlian.apipedido.controller.service.UsuarioService;
import br.com.thiagowlian.apipedido.dto.ProdutoConsultaDetalhesDto;
import br.com.thiagowlian.apipedido.dto.UsuarioConsultaPorIdDto;
import br.com.thiagowlian.apipedido.dto.UsuarioDto;
import br.com.thiagowlian.apipedido.dto.UsuarioForm;
import br.com.thiagowlian.apipedido.model.ProdutoModel;
import br.com.thiagowlian.apipedido.model.UsuarioModel;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuario){
		usuarioService.cadastrarUsuario(usuario);
		UsuarioDto usuarioDtoResponse = new UsuarioDto(usuario);
		return ResponseEntity.created(null).body(usuarioDtoResponse);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioConsultaPorIdDto> buscarUsuario(@PathVariable int id){
		Optional<UsuarioModel> produto = usuarioService.buscarUsuarioPorId(id);
		if(produto.isPresent()) {
			return ResponseEntity.ok(new UsuarioConsultaPorIdDto(produto.get()));
		}
		return ResponseEntity.notFound().build();
	}
}
