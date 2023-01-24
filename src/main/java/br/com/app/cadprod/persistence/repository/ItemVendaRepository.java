package br.com.app.cadprod.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.cadprod.persistence.entity.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

}
