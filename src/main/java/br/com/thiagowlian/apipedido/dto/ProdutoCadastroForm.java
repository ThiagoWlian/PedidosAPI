package br.com.thiagowlian.apipedido.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.thiagowlian.apipedido.model.ProdutoModel;

public class ProdutoCadastroForm {
	@NotBlank
	String nome;
	@NotBlank
	String descricao;
	@NotNull
	float valor;
	
	public ProdutoCadastroForm() {}
	
	public ProdutoCadastroForm(ProdutoModel produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
	}

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

	public ProdutoModel converterParaProduto(){
		return new ProdutoModel(this.nome, this.descricao, this.valor);
	}
}
