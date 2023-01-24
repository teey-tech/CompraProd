package br.com.app.cadprod.service.impl;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

import br.com.app.cadprod.exception.message.ObjectNotFoundException;
import br.com.app.cadprod.persistence.entity.ItemVenda;
import br.com.app.cadprod.persistence.entity.Produto;
import br.com.app.cadprod.persistence.entity.Usuario;
import br.com.app.cadprod.persistence.entity.Venda;
import br.com.app.cadprod.persistence.repository.ItemVendaRepository;
import br.com.app.cadprod.persistence.repository.ProdutoRepository;
import br.com.app.cadprod.persistence.repository.UsuarioRepository;
import br.com.app.cadprod.persistence.repository.VendaRepository;
import br.com.app.cadprod.service.VendaService;

@Service
@Transactional
public class VendaServiceImpl implements VendaService {

  private static final Logger LOGGER = LoggerFactory.getLogger(VendaServiceImpl.class);

  @Autowired
  private VendaRepository repository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private ItemVendaRepository itemRepository;

  @Override
  public Venda comprar(Venda venda) {

    LOGGER.info("Efetuando Compra");
    notNull(venda, "Usuario invalido");

    Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(venda.getUsuario().getId());

    Optional<Produto> produtoEncontrado = produtoRepository.findById(venda.getProduto().getId());

    if (usuarioEncontrado.isEmpty()) {
      throw new ObjectNotFoundException("Usuario não Encontrado!");
    }

    if (produtoEncontrado.isEmpty()) {
      throw new ObjectNotFoundException("Produto não encontrado");
    }

    venda.setId(venda.getId());
    venda.setCodigoVenda(UUID.randomUUID().toString());
    venda.setStatusVenda("Compra Aprovada com Sucesso Salve o codido da venda");
    venda.setUsuario(usuarioEncontrado.get());
    venda.setProduto(produtoEncontrado.get());

    if (produtoEncontrado.get().getQuantidade() == 0) {
      throw new ObjectNotFoundException("Produto em falta no Estoque Mandaremos um e-mail quando estiver disponivel");
    } else {
      ItemVenda itemVendido = new ItemVenda();
      itemVendido.setNomeProduto(produtoEncontrado.get().getNomeProduto());
      itemVendido.setNomeComprador(usuarioEncontrado.get().getNome());
      itemVendido.setQuantidadeVendida(1);
      itemRepository.saveAndFlush(itemVendido);

      produtoEncontrado.get().setQuantidade(produtoEncontrado.get().getQuantidade() - 1);
      produtoRepository.save(produtoEncontrado.get());
    }
    return repository.saveAndFlush(venda);
  }

}
