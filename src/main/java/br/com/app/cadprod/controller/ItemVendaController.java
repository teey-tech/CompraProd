package br.com.app.cadprod.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.cadprod.mapper.ItemVendaMapper;
import br.com.app.cadprod.model.response.ItemVendaResponse;
import br.com.app.cadprod.persistence.entity.ItemVenda;
import br.com.app.cadprod.service.impl.ItemVendaServiceImpl;

@RestController
@RequestMapping("v1/auditoria")
@CrossOrigin(allowedHeaders = "*", maxAge = 2500)
public class ItemVendaController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ItemVendaController.class);

  @Autowired
  private ItemVendaServiceImpl service;

  @Autowired
  private ItemVendaMapper mapper;

  @GetMapping
  public ResponseEntity<List<ItemVendaResponse>> findAll() {
    LOGGER.info("Buscando todos os registros");
    List<ItemVenda> auditoriaList = service.findAll();
    List<ItemVendaResponse> auditoriaResponseList = mapper.toItemVendaResponseList(auditoriaList);
    return ResponseEntity.status(HttpStatus.OK).body(auditoriaResponseList);
  }

}
