package br.com.app.cadprod.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaResponse {

  private Long id;

  private String codigoVenda;

  private String statusVenda;

  private ProdutoResponse produto;

  private UsuarioResponse usuario;
}
