package br.com.app.cadprod.service;

import java.util.List;

import br.com.app.cadprod.persistence.entity.Produto;

public interface ProdutoService {

  Produto create(Produto produto);

  Produto findById(Long id);

  List<Produto> findAll();

  Produto update(Long id, Produto produto);

  void delete(Long id);

  void findByNomeProduto(Produto produto);

}
