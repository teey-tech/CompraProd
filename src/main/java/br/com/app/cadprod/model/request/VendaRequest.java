package br.com.app.cadprod.model.request;

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
public class VendaRequest {

  @JsonIgnore
  private Long id;

  @JsonIgnore
  private String codigoVenda;

  @JsonIgnore
  private String statusVenda;

  @NotNull
  private Long usuarioId;

  @NotNull
  private Long produtoId;
}
