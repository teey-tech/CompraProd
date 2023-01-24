package br.com.app.cadprod.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {

  private Long id;

  private String nomeProduto;

  private Integer quantidade;

  private Double preco;

  private String marca;
}
