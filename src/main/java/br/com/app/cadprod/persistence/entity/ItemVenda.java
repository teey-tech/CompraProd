package br.com.app.cadprod.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_auditoria_de_venda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemVenda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome_comprador", nullable = false)
  private String nomeComprador;

  @Column(name = "nome_produto", nullable = false)
  private String nomeProduto;

  @Column(name = "quantidade_vendida", nullable = false)
  private Integer quantidadeVendida;

}
