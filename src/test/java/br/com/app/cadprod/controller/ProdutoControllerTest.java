package br.com.app.cadprod.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.app.cadprod.mapper.ProdutoMapper;
import br.com.app.cadprod.model.request.ProdutoRequest;
import br.com.app.cadprod.model.response.ProdutoResponse;
import br.com.app.cadprod.persistence.entity.Produto;
import br.com.app.cadprod.service.impl.ProdutoServiceImpl;

@SpringBootTest
public class ProdutoControllerTest {

    private static final Integer INDEX = 0;

    @InjectMocks
    private ProdutoController controller;

    @Mock
    private ProdutoServiceImpl service;

    @Mock
    private ProdutoMapper mapper;

    private static final Long ID = (long) 1;
    private static final String NOME_PRODUTO = "Iphone 12";
    private static final Integer QUANTIDADE = 5;
    private static final Double PRECO = 12.50;
    private static final String MARCA = "Iphone";

    private Produto produto;
    private ProdutoResponse response;

    private ProdutoRequest request;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startProduto();
    }

    @Test
    void whenCreateReturnCreated() {
        when(service.create(any())).thenReturn(produto);
       
        ResponseEntity<ProdutoResponse> responseP = controller.create(request);
        assertEquals(HttpStatus.CREATED, responseP.getStatusCode());
        assertEquals(ResponseEntity.class, responseP.getClass());
    }

    @Test
    void whenFindByIdThenReturnSucces(){
        when(service.findById(anyLong())).thenReturn(produto);
        when(mapper.toProduto(request)).thenReturn(produto);
        when(mapper.toProdutoResponse(produto)).thenReturn(response);

        ResponseEntity<ProdutoResponse> responseP = controller.findById(ID);

        assertNotNull(responseP);
        assertNotNull(responseP.getBody());

        assertEquals(ResponseEntity.class, responseP.getClass());
        assertEquals(ProdutoResponse.class, responseP.getBody().getClass() );

        assertEquals(ID, responseP.getBody().getId());
        assertEquals(NOME_PRODUTO, responseP.getBody().getNomeProduto());
        assertEquals(QUANTIDADE, responseP.getBody().getQuantidade());
        assertEquals(PRECO, responseP.getBody().getPreco());
        assertEquals(MARCA, responseP.getBody().getMarca());

    }

    @Test
    void whenFindAllThenReturnAListOfProdutoResponse() {
        when(service.findAll()).thenReturn(List.of(produto));
       when(mapper.toProdutoResponseList(List.of(produto))).thenReturn(List.of(response));

        ResponseEntity<List<ProdutoResponse>> responseP = controller.findAll();

        assertNotNull(responseP);
        assertNotNull(responseP.getBody());

        assertEquals(HttpStatus.OK, responseP.getStatusCode());
        assertEquals(ResponseEntity.class, responseP.getClass());
        assertEquals(ProdutoResponse.class, responseP.getBody().get(INDEX).getClass());
        

        assertEquals(ID, responseP.getBody().get(INDEX).getId());
        assertEquals(NOME_PRODUTO, responseP.getBody().get(INDEX).getNomeProduto());
        assertEquals(QUANTIDADE, responseP.getBody().get(INDEX).getQuantidade());
        assertEquals(PRECO, responseP.getBody().get(INDEX).getPreco());
        assertEquals(MARCA, responseP.getBody().get(INDEX).getMarca());

    }

    @Test
    void whenUpdateThenReturnSuccess(){
        when(service.update(ID, produto)).thenReturn(produto);
        when(mapper.toProduto(request)).thenReturn(produto);
        when(mapper.toProdutoResponse(produto)).thenReturn(response);

        ResponseEntity<ProdutoResponse> responseP = controller.update(ID, request);

        assertNotNull(responseP);
        assertNotNull(responseP.getBody());

        assertEquals(ResponseEntity.class, responseP.getClass());
        assertEquals(ProdutoResponse.class, responseP.getBody().getClass());
        assertEquals(HttpStatus.OK, responseP.getStatusCode());

        assertEquals(ID, responseP.getBody().getId());
        assertEquals(NOME_PRODUTO, responseP.getBody().getNomeProduto());
        assertEquals(QUANTIDADE, responseP.getBody().getQuantidade());
        assertEquals(PRECO, responseP.getBody().getPreco());
        assertEquals(MARCA, responseP.getBody().getMarca());

    }

    @Test
    void whenDeleteWhitSuccess() {
        doNothing().when(service).delete(anyLong());
        ResponseEntity<Void> responseP = controller.delete(ID);

        assertNotNull(responseP);

        assertEquals(HttpStatus.OK, responseP.getStatusCode());
        assertEquals(ResponseEntity.class, responseP.getClass());

        verify(service, times(1)).delete(anyLong());

    }

    private void startProduto() {
        produto = new Produto(ID, NOME_PRODUTO, QUANTIDADE, PRECO, MARCA, null);
        response = new ProdutoResponse(ID, NOME_PRODUTO, QUANTIDADE, PRECO, MARCA);
        request = new ProdutoRequest(NOME_PRODUTO, QUANTIDADE, PRECO, MARCA);
    }
}
