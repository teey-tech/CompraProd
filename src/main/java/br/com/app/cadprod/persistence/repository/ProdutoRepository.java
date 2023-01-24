package br.com.app.cadprod.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.cadprod.persistence.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  Optional<Produto> findByNomeProduto(String nomeProduto);
}
