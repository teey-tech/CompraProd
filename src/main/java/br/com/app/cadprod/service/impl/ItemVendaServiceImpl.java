package br.com.app.cadprod.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.cadprod.persistence.entity.ItemVenda;
import br.com.app.cadprod.persistence.repository.ItemVendaRepository;
import br.com.app.cadprod.service.ItemVendaService;

@Service
public class ItemVendaServiceImpl implements ItemVendaService {

  private static final Logger LOGGER = LoggerFactory.getLogger(VendaServiceImpl.class);

  @Autowired
  private ItemVendaRepository repository;

  @Override
  public List<ItemVenda> findAll() {
    LOGGER.info("Buscando todos registros no banco");
    return repository.findAll();
  }

}
