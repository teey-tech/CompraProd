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
@Table(name = "tb_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome_produto", unique = true)
  private String nomeProduto;

  @Column(name = "quantidade")
  private Integer quantidade;

  @Column(name = "preco")
  private Double preco;

  @Column(name = "marca")
  private String marca;

  @OneToMany(mappedBy = "produto", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("produto")
  private List<Venda> venda;

}
