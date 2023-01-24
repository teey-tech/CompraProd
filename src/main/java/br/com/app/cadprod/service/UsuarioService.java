package br.com.app.cadprod.service;

import java.util.List;

import br.com.app.cadprod.persistence.entity.Usuario;

public interface UsuarioService {

  Usuario create(Usuario usuario);

  Usuario findById(Long id);

  List<Usuario> findAll();

  Usuario update(Long id, Usuario usuario);

  void delete(Long id);

  void findByEmail(Usuario usuario);

  void findByCpf(Usuario usuario);

}
