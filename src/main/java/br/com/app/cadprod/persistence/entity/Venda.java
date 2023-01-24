package br.com.app.cadprod.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_venda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "codigo_de_venda", nullable = false)
  private String codigoVenda;

  @Column(name = "status_venda", nullable = false)
  private String statusVenda;

  @ManyToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  @JsonIgnoreProperties("venda")
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "produto_id", referencedColumnName = "id")
  @JsonIgnoreProperties("venda")
  private Produto produto;

}
