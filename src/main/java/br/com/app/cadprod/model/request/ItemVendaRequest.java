package br.com.app.cadprod.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemVendaRequest {

  @JsonIgnore
  private Long id;

  @NotBlank
  private String nomeComprador;

  @NotBlank
  private String nomeProduto;

  @NotNull
  private Integer quantidadeVendida;
}
