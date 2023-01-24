package br.com.app.cadprod.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.cadprod.mapper.UsuarioMapper;
import br.com.app.cadprod.model.request.UsuarioRequest;
import br.com.app.cadprod.model.response.UsuarioResponse;
import br.com.app.cadprod.persistence.entity.Usuario;
import br.com.app.cadprod.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("v1/usuario")
@CrossOrigin(allowedHeaders = "*", maxAge = 2500)
public class UsuarioController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

  @Autowired
  private UsuarioServiceImpl service;

  @Autowired
  private UsuarioMapper mapper;

  @PostMapping
  public ResponseEntity<UsuarioResponse> create(@Valid @RequestBody UsuarioRequest request) {
    LOGGER.info("Requisição recebida {} ", request);
    Usuario usuario = mapper.toUsuario(request);
    Usuario usuarioSalvo = service.create(usuario);
    UsuarioResponse usuarioResponse = mapper.toUsuarioResponse(usuarioSalvo);
    return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
  }

  @GetMapping
  public ResponseEntity<List<UsuarioResponse>> findAll() {
    LOGGER.info("Buscando todos os registros");
    List<Usuario> usuarioList = service.findAll();
    List<UsuarioResponse> usuarioResponseList = mapper.toUsuarioResponseList(usuarioList);
    return ResponseEntity.status(HttpStatus.OK).body(usuarioResponseList);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<UsuarioResponse> findById(@PathVariable("id") Long id) {
    LOGGER.info("Buscando o registro {} ", id);
    Usuario findById = service.findById(id);
    UsuarioResponse usuarioResponse = mapper.toUsuarioResponse(findById);
    return ResponseEntity.status(HttpStatus.OK).body(usuarioResponse);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<UsuarioResponse> update(@Valid @RequestBody @PathVariable("id") Long id,
      UsuarioRequest request) {
    LOGGER.info("Atualizando o registro {} ", id);
    Usuario usuario = mapper.toUsuario(request);
    Usuario usuarioAtualizado = service.update(id, usuario);
    UsuarioResponse usuarioResponse = mapper.toUsuarioResponse(usuarioAtualizado);
    return ResponseEntity.status(HttpStatus.OK).body(usuarioResponse);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    LOGGER.info("Deletando Registro {} ", id);
    service.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
