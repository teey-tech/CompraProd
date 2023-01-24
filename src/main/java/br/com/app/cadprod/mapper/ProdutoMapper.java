package br.com.app.cadprod.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.app.cadprod.model.request.ProdutoRequest;
import br.com.app.cadprod.model.response.ProdutoResponse;
import br.com.app.cadprod.persistence.entity.Produto;

@Component
public class ProdutoMapper {

  @Autowired
  private ModelMapper mapper;

  public Produto toProduto(ProdutoRequest request) {
    return mapper.map(request, Produto.class);
  }

  public ProdutoResponse toProdutoResponse(Produto produto) {
    return mapper.map(produto, ProdutoResponse.class);
  }

  public List<ProdutoResponse> toProdutoResponseList(List<Produto> produtoList) {
    return produtoList.stream()
        .map(this::toProdutoResponse)
        .collect(Collectors.toList());

  }
}
