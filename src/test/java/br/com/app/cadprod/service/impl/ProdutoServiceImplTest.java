package br.com.app.cadprod.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.app.cadprod.exception.message.DataViolationException;
import br.com.app.cadprod.exception.message.ObjectNotFoundException;
import br.com.app.cadprod.persistence.entity.Produto;
import br.com.app.cadprod.persistence.repository.ProdutoRepository;

@SpringBootTest
public class ProdutoServiceImplTest {

  @InjectMocks
  private ProdutoServiceImpl service;

  @Mock
  private ProdutoRepository repository;

  private Produto produto;

  private Optional<Produto> optionalProduto;

  private static final Long ID = (long) 1;
  private static final String NOME_PRODUTO = "Iphone 12";
  private static final Integer QUANTIDADE = 5;
  private static final Double PRECO = 12.50;
  private static final String MARCA = "Iphone";

  private static final Integer INDEX = 0;

  private static final String PRODUTO_NÃO_ENCONTRADO = "Produto não encontrado";
  private static final String PRODUTO_JÁ_CADASTRADO_NO_SISTEMA = "Produto já cadastrado no sistema";

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    startProduto();
  }

  @Test
  void whenCreateAndReturnSuccess(){
    when(repository.saveAndFlush(any())).thenReturn(produto);
    Produto response = service.create(produto);

    assertNotNull(response);

    assertEquals(Produto.class, response.getClass());
    assertEquals(NOME_PRODUTO, response.getNomeProduto());
    assertEquals(PRECO, response.getPreco());
    assertEquals(MARCA, response.getMarca());

  }

  @Test
  void whenCreateAndReturnADataViolationException(){
    when(repository.findByNomeProduto(anyString())).thenReturn(optionalProduto);
    try {
      optionalProduto.get().setId((long) 2);
      service.create(produto);
    } catch (Exception ex) {
      assertEquals(DataViolationException.class, ex.getClass());
      assertEquals(PRODUTO_JÁ_CADASTRADO_NO_SISTEMA, ex.getMessage());
    }
  }

  @Test
  void whenFindByIdThenReturnAnProdutoInstance(){
    when(repository.findById(anyLong())).thenReturn(optionalProduto);
    Produto response = service.findById(ID);
    assertNotNull(response);


    assertEquals(Produto.class, response.getClass());
    assertEquals(ID, response.getId());
    assertEquals(NOME_PRODUTO, response.getNomeProduto());
    assertEquals(PRECO, response.getPreco());
    assertEquals(MARCA, response.getMarca());

  }

  @Test
  void whenFindByIdThenReturnAnObjectNotFoundException(){
    when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException(PRODUTO_NÃO_ENCONTRADO));
    try {
      service.findById(ID);
    } catch (Exception ex) {
      assertEquals(ObjectNotFoundException.class, ex.getClass());
      assertEquals(PRODUTO_NÃO_ENCONTRADO, ex.getMessage());
    }
  }

  @Test
  void whenFindAllThenReturnAListOfProduct(){
    when(repository.findAll()).thenReturn(List.of(produto));
    List<Produto> response = service.findAll();

    assertNotNull(response);
    
    assertEquals(Produto.class, response.get(INDEX).getClass());
    assertEquals(1, response.size());
    assertEquals(ID, response.get(INDEX).getId());
    assertEquals(NOME_PRODUTO, response.get(INDEX).getNomeProduto());
    assertEquals(QUANTIDADE, response.get(INDEX).getQuantidade());
    assertEquals(PRECO, response.get(INDEX).getPreco());
    assertEquals(MARCA, response.get(INDEX).getMarca());

  }

  @Test
  void whenUpdateThenReturnSuccess(){
    when(repository.saveAndFlush(any())).thenReturn(produto);
    
    Produto response = service.update(ID, produto);

    assertNotNull(response);

    assertEquals(Produto.class, response.getClass());

    assertEquals(ID, response.getId());
    assertEquals(NOME_PRODUTO, response.getNomeProduto());
    assertEquals(PRECO, response.getPreco());
    assertEquals(MARCA, response.getMarca());
  }

  @Test
  void whenUpdateThenReturnADataViolationException(){
    when(repository.saveAndFlush(any())).thenReturn(produto);
    try {
      produto.setId((long) 2);
      service.update(ID, produto);
    } catch (Exception ex) {
      assertEquals(DataViolationException.class, ex.getClass() );
      assertEquals(PRODUTO_JÁ_CADASTRADO_NO_SISTEMA, ex.getMessage());
    }
  }

  @Test
  void whenDeleteWithSucces(){
    when(repository.findById(anyLong())).thenReturn(optionalProduto);
    doNothing().when(repository).deleteById(ID);
    service.delete(ID);
    verify(repository, times(1)).deleteById(anyLong());
  }

  @Test
  void whenDeleteReturnAObjectNotFoundException(){
    when(repository.findById(anyLong()))
      .thenThrow(new ObjectNotFoundException(PRODUTO_NÃO_ENCONTRADO));
    
      try {
        service.delete(ID);
      } catch (Exception ex) {
          assertEquals(ObjectNotFoundException.class, ex.getClass());
          assertEquals(PRODUTO_NÃO_ENCONTRADO, ex.getMessage());
      }
  }

  private void startProduto() {
    produto = new Produto(ID, NOME_PRODUTO, QUANTIDADE, PRECO, MARCA, null);
    optionalProduto = Optional.of(new Produto(ID, NOME_PRODUTO, QUANTIDADE, PRECO, MARCA, null));
  }

}
