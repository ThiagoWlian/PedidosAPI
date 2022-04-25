package br.com.thiagowlian.apipedido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thiagowlian.apipedido.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer>{
	public Optional<UsuarioModel> findByNome(String nome);
}
