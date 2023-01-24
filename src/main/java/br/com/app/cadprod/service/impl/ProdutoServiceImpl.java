package br.com.app.cadprod.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

import br.com.app.cadprod.exception.message.DataViolationException;
import br.com.app.cadprod.exception.message.ObjectNotFoundException;
import br.com.app.cadprod.persistence.entity.Produto;
import br.com.app.cadprod.persistence.repository.ProdutoRepository;
import br.com.app.cadprod.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoServiceImpl.class);

  @Autowired
  private ProdutoRepository repository;

  @Override
  public Produto create(Produto produto) {
    LOGGER.info("Criando um Registro de Produto");
    notNull(produto, "Produto invalido");
    this.findByNomeProduto(produto);
    return repository.saveAndFlush(produto);
  }

  @Override
  public Produto findById(Long id) {
    LOGGER.info("Buscando Registro por id");
    notNull(id, "Id invalido");
    Optional<Produto> findById = repository.findById(id);
    return findById.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
  }

  @Override
  public List<Produto> findAll() {
    LOGGER.info("Buscando todos os Produtos cadastrados");
    return repository.findAll();
  }

  @Override
  public Produto update(Long id, Produto produto) {
    LOGGER.info("Atualizando Registro no banco de dados");
    notNull(id, "iD INVALIDO");
    notNull(produto, "Erro ao atualizar produto");
    this.findByNomeProduto(produto);
    produto.setId(id);
    return repository.saveAndFlush(produto);
  }

  @Override
  public void delete(Long id) {
    LOGGER.info("Deletando um Produto");
    notNull(id, "Id invalido");
    this.findById(id);
    repository.deleteById(id);
  }

  @Override
  public void findByNomeProduto(Produto produto) {
    LOGGER.info("Encontrando produto por nome");
    notNull(produto, "Requisição Falha");
    Optional<Produto> findByNomeProduto = repository.findByNomeProduto(produto.getNomeProduto());
    if (findByNomeProduto.isPresent() && !findByNomeProduto.get().getId().equals(produto.getId())) {
      throw new DataViolationException("Produto já cadastrado no sistema");
    }

  }

}
