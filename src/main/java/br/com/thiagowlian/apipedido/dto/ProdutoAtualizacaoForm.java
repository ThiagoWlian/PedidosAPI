package br.com.thiagowlian.apipedido.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.thiagowlian.apipedido.model.ProdutoModel;

public class ProdutoAtualizacaoForm {
	@NotBlank
	String nome;
	@NotBlank
	String descricao;
	@NotNull
	float valor;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public Optional<ProdutoModel> gerarNovoProdutoModel(Optional<ProdutoModel> produto) {
		produto.get().setNome(nome);
		produto.get().setDescricao(descricao);
		produto.get().setValor(valor);
		return produto;
	}
}
