package br.com.thiagowlian.apipedido.controller.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.thiagowlian.apipedido.dto.ProdutoAtualizacaoForm;
import br.com.thiagowlian.apipedido.dto.ProdutoCadastroForm;
import br.com.thiagowlian.apipedido.model.ProdutoModel;
import br.com.thiagowlian.apipedido.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public void cadastrarProduto(ProdutoCadastroForm produtoForm) {
		ProdutoModel produtoModel = produtoForm.converterParaProduto();
		produtoRepository.save(produtoModel);
	}
	
	public Optional<ProdutoModel> buscarProdutoPeloId(int id) {
		Optional<ProdutoModel> produto = produtoRepository.findById(id);
		return produto;
	}
	
	public Page<ProdutoModel> buscarTodosProdutos(Pageable page) {
		Page<ProdutoModel> listaProduto = produtoRepository.findAll(page);
		return listaProduto;
	}
	
	@Transactional
	public Optional<ProdutoModel> atualizarProdutoPeloId(int id, ProdutoAtualizacaoForm produtoForm) {
		Optional<ProdutoModel> produtoModel = produtoRepository.findById(id);//Não é uma boa prática! Alterar dps
		if(produtoModel.isPresent()) {
			produtoModel  = produtoForm.gerarNovoProdutoModel(produtoModel);
			return produtoModel;
		}
		return produtoModel;
	}
	
	@Transactional
	public void removerProdutoPeloId(int id) {
		produtoRepository.deleteById(id);
	}
}
