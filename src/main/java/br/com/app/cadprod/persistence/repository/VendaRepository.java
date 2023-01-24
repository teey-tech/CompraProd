package br.com.app.cadprod.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.cadprod.persistence.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}
