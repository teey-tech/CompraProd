package br.com.app.cadprod.persistence.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome", nullable = false)
  private String nome;

  @Column(name = "cpf", nullable = false, length = 11)
  private String cpf;

  @Column(name = "email", nullable = false)
  private String email;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("usuario")
  private List<Venda> venda;
}
