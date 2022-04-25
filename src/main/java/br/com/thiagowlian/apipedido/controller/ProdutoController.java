package br.com.thiagowlian.apipedido.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagowlian.apipedido.controller.service.ProdutoService;
import br.com.thiagowlian.apipedido.dto.ProdutoConsultaDetalhesDto;
import br.com.thiagowlian.apipedido.dto.ProdutoDto;
import br.com.thiagowlian.apipedido.dto.ProdutoAtualizacaoForm;
import br.com.thiagowlian.apipedido.dto.ProdutoCadastroForm;
import br.com.thiagowlian.apipedido.model.ProdutoModel;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@PostMapping
	@CacheEvict(value = "listaProdutos", allEntries = true)
	public ResponseEntity<ProdutoDto> cadastrarProduto(@RequestBody @Valid ProdutoCadastroForm produtoForm){
		produtoService.cadastrarProduto(produtoForm);
		return ResponseEntity.created(null).body(new ProdutoDto(produtoForm));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoConsultaDetalhesDto> detalharProduto(@PathVariable int id){
		Optional<ProdutoModel> produto = produtoService.buscarProdutoPeloId(id);
		if(produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoConsultaDetalhesDto(produto.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/listarProdutos")
	@Cacheable(value = "listaProdutos")
	public ResponseEntity<Page<ProdutoModel>> listarProduto(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable page){
		Page<ProdutoModel> listaProduto = produtoService.buscarTodosProdutos(page);
		if(listaProduto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(listaProduto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> atualizarDadosProduto(@PathVariable int id, @RequestBody @Valid ProdutoAtualizacaoForm produtoForm){
		 Optional<ProdutoModel> produto = produtoService.atualizarProdutoPeloId(id, produtoForm);
		 if(produto.isPresent()) {
			 return ResponseEntity.ok(new ProdutoDto(produto.get()));
		 }
		 return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarProduto(@PathVariable int id){
		produtoService.removerProdutoPeloId(id);
		return ResponseEntity.ok().build();
	}
}
