package br.com.app.cadprod.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemVendaResponse {

  private Long id;

  private String nomeComprador;

  private String nomeProduto;

  private Integer quantidadeVendida;
}
