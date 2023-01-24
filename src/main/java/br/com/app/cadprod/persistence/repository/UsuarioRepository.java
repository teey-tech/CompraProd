package br.com.app.cadprod.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.cadprod.persistence.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByEmail(String email);

  Optional<Usuario> findByCpf(String cpf);
}
