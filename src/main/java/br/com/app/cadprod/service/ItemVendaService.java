package br.com.app.cadprod.service;

import java.util.List;

import br.com.app.cadprod.persistence.entity.ItemVenda;

public interface ItemVendaService {

  List<ItemVenda> findAll();
}
