package br.com.app.cadprod.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

  private Long id;

  private String nome;

  private String cpf;

  private String email;
}
