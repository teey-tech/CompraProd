package br.com.app.cadprod.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

  @NotBlank(message = "Nome do Produto é Obrigatório")
  private String nomeProduto;

  @NotNull(message = "Quantodade é obrigatorio")
  private Integer quantidade;

  @NotNull(message = "Preço é Obrigatorio")
  private Double preco;

  @NotBlank(message = "Marca é obrigatorios")
  private String marca;
}
