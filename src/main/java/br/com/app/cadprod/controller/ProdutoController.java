package br.com.app.cadprod.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.cadprod.mapper.ProdutoMapper;
import br.com.app.cadprod.model.request.ProdutoRequest;
import br.com.app.cadprod.model.response.ProdutoResponse;
import br.com.app.cadprod.persistence.entity.Produto;
import br.com.app.cadprod.service.impl.ProdutoServiceImpl;

@RestController
@RequestMapping(value = "v1/produto")
@CrossOrigin(allowedHeaders = "*", maxAge = 3500)
public class ProdutoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

  @Autowired
  private ProdutoServiceImpl service;

  @Autowired
  private ProdutoMapper mapper;

  @PostMapping
  public ResponseEntity<ProdutoResponse> create(@Valid @RequestBody ProdutoRequest request) {
    LOGGER.info("Requisição recebida {} ", request);
    Produto produto = mapper.toProduto(request);
    Produto produtoSalvo = service.create(produto);
    ProdutoResponse produtoResponse = mapper.toProdutoResponse(produtoSalvo);
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
  }

  @GetMapping
  public ResponseEntity<List<ProdutoResponse>> findAll() {
    LOGGER.info("Buscando todos os registros");
    List<Produto> produtoList = service.findAll();
    List<ProdutoResponse> produtoResponseList = mapper.toProdutoResponseList(produtoList);
    return ResponseEntity.status(HttpStatus.OK).body(produtoResponseList);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ProdutoResponse> findById(@PathVariable("id") Long id) {
    LOGGER.info("Buscando o registro {} ", id);
    Produto findById = service.findById(id);
    ProdutoResponse produtoResponse = mapper.toProdutoResponse(findById);
    return ResponseEntity.status(HttpStatus.OK).body(produtoResponse);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ProdutoResponse> update(@Valid @RequestBody @PathVariable("id") Long id,
      ProdutoRequest request) {
    LOGGER.info("Atualizando o registro {} ", id);
    Produto produto = mapper.toProduto(request);
    Produto produtoAtualizado = service.update(id, produto);
    ProdutoResponse produtoResponse = mapper.toProdutoResponse(produtoAtualizado);
    return ResponseEntity.status(HttpStatus.OK).body(produtoResponse);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    LOGGER.info("Deletando Registro {} ", id);
    service.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
