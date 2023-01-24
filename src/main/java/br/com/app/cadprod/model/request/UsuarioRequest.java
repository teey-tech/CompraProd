package br.com.app.cadprod.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

  @JsonIgnore
  private Long id;

  @NotBlank(message = "Nome é Obrigatório")
  private String nome;

  @NotBlank(message = "CPF é Obrigatório")
  @CPF
  private String cpf;

  @NotBlank(message = "Email é Obrigatório")
  @Email
  private String email;
}
