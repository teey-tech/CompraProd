package br.com.app.cadprod.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

import br.com.app.cadprod.exception.message.DataViolationException;
import br.com.app.cadprod.exception.message.ObjectNotFoundException;
import br.com.app.cadprod.persistence.entity.Usuario;
import br.com.app.cadprod.persistence.repository.UsuarioRepository;
import br.com.app.cadprod.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);

  @Autowired
  private UsuarioRepository repository;

  @Override
  public Usuario create(Usuario usuario) {
    LOGGER.info("Criando um Registro de Usuario");
    notNull(usuario, "Usuario invalido");

    /* Checando se Email já está cadastrado no sistema */
    this.findByEmail(usuario);

    /* Checando se CPF já está cadastrado no sistema */
    this.findByCpf(usuario);

    return repository.saveAndFlush(usuario);
  }

  @Override
  public Usuario findById(Long id) {
    LOGGER.info("Buscando Registro por id");
    notNull(id, "Id invalido");

    Optional<Usuario> usuarioEncontrado = repository.findById(id);
    return usuarioEncontrado.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado"));
  }

  @Override
  public List<Usuario> findAll() {
    return repository.findAll();
  }

  @Override
  public Usuario update(Long id, Usuario usuario) {
    LOGGER.info("Atualizando Registro no banco de dados");
    notNull(id, "iD INVALIDO");
    notNull(usuario, "Erro ao atualizar produto");

    /* Checando se existe um id com esse valor no banco de dados */
    this.findById(id);

    /* Checando se Email já está cadastrado no sistema */
    this.findByEmail(usuario);

    /* Checando se CPF já está cadastrado no sistema */
    this.findByCpf(usuario);

    return repository.saveAndFlush(usuario);
  }

  @Override
  public void delete(Long id) {
    LOGGER.info("Deletando um Produto");
    notNull(id, "Id invalido");
    this.findById(id);
    repository.deleteById(id);

  }

  @Override
  public void findByEmail(Usuario usuario) {
    LOGGER.info("Encontrando Usuario por email");
    notNull(usuario, "Requisição Falha");
    Optional<Usuario> emailEncontrado = repository.findByEmail(usuario.getEmail());
    if (emailEncontrado.isPresent() && !emailEncontrado.get().getId().equals(usuario.getId())) {
      throw new DataViolationException("Email já cadastrado no sistema");
    }

  }

  @Override
  public void findByCpf(Usuario usuario) {
    LOGGER.info("Encontrando Usuario por cpf");
    notNull(usuario, "Requisição Falha");

    Optional<Usuario> cpfEncontrado = repository.findByCpf(usuario.getCpf());

    if (cpfEncontrado.isPresent() && !cpfEncontrado.get().getId().equals(usuario.getId())) {
      throw new DataViolationException("CPF já cadastrado no sistema");
    }

  }

}
