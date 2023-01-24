package br.com.app.cadprod.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.cadprod.mapper.VendaMapper;
import br.com.app.cadprod.model.request.VendaRequest;
import br.com.app.cadprod.model.response.VendaResponse;
import br.com.app.cadprod.persistence.entity.Venda;
import br.com.app.cadprod.service.impl.VendaServiceImpl;

@RestController
@RequestMapping("v1/venda")
@CrossOrigin(allowedHeaders = "*", maxAge = 3500)
public class VendaController {

  private static final Logger LOGGER = LoggerFactory.getLogger(VendaController.class);

  @Autowired
  private VendaServiceImpl service;

  @Autowired
  private VendaMapper mapper;

  @PostMapping
  public ResponseEntity<VendaResponse> comprar(@Valid @RequestBody VendaRequest request) {
    LOGGER.info("Requisição recebida {} ", request);

    Venda venda = mapper.toVenda(request);
    Venda comprar = service.comprar(venda);
    VendaResponse vendaResponse = mapper.toVendaResponse(comprar);

    return ResponseEntity.status(HttpStatus.CREATED).body(vendaResponse);
  }
}
