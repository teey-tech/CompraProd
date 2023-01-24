package br.com.app.cadprod.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.app.cadprod.mapper.UsuarioMapper;
import br.com.app.cadprod.model.request.UsuarioRequest;
import br.com.app.cadprod.model.response.UsuarioResponse;
import br.com.app.cadprod.persistence.entity.Usuario;
import br.com.app.cadprod.service.impl.UsuarioServiceImpl;

@SpringBootTest
public class UsuarioControllerTest {

  @InjectMocks
  private UsuarioController controller;

  @Mock
  private UsuarioServiceImpl service;

  @Mock
  private UsuarioMapper mapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    startUsuario();
  }

  private Usuario usuario;

  private Optional<Usuario> optionalUsuario;

  private UsuarioResponse usuarioResponse;

  private UsuarioRequest usuarioRequest;

  private static final Long ID = (long) 1;
  private static final String NOME = "Teste";
  private static final String CPF = "69172763086";
  private static final String EMAIL = "teste@teste.com";
  private static final Integer INDEX = 0;

  @Test
  void whenCreateReturnCreated() {
      when(service.create(any())).thenReturn(usuario);
     
      ResponseEntity<UsuarioResponse> response = controller.create(usuarioRequest);
      assertEquals(HttpStatus.CREATED, response.getStatusCode());
      assertEquals(ResponseEntity.class, response.getClass());
  }

  @Test
  void whenFindByIdThenReturnSuccess(){
      when(service.findById(anyLong())).thenReturn(usuario);
      when(mapper.toUsuario(usuarioRequest)).thenReturn(usuario);
      when(mapper.toUsuarioResponse(usuario)).thenReturn(usuarioResponse);

      ResponseEntity<UsuarioResponse> response = controller.findById(ID);

      assertNotNull(response);
      assertNotNull(response.getBody());

      assertEquals(ResponseEntity.class, response.getClass());
      assertEquals(UsuarioResponse.class, response.getBody().getClass() );

      assertEquals(ID, response.getBody().getId());
      assertEquals(NOME, response.getBody().getNome());
      assertEquals(CPF, response.getBody().getCpf());
      assertEquals(EMAIL, response.getBody().getEmail());


  }

  @Test
  void whenFindAllThenReturnAListOfUsuarioResponse() {
      when(service.findAll()).thenReturn(List.of(usuario));
     when(mapper.toUsuarioResponseList(List.of(usuario))).thenReturn(List.of(usuarioResponse));

    ResponseEntity<List<UsuarioResponse>> response = controller.findAll();

      assertNotNull(response);
      assertNotNull(response.getBody());

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(ResponseEntity.class, response.getClass());
      assertEquals(UsuarioResponse.class, response.getBody().get(INDEX).getClass());
      

      assertEquals(ID, response.getBody().get(INDEX).getId());
      assertEquals(NOME, response.getBody().get(INDEX).getNome());
      assertEquals(CPF, response.getBody().get(INDEX).getCpf());
      assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());

  }

  @Test
  void whenUpdateThenReturnSuccess(){
      when(service.update(ID, usuario)).thenReturn(usuario);
      when(mapper.toUsuario(usuarioRequest)).thenReturn(usuario);
      when(mapper.toUsuarioResponse(usuario)).thenReturn(usuarioResponse);

    ResponseEntity<UsuarioResponse> response = controller.update(ID, usuarioRequest);

      assertNotNull(response);
      assertNotNull(response.getBody());

      assertEquals(ResponseEntity.class, response.getClass());
      assertEquals(UsuarioResponse.class, response.getBody().getClass());
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(ID, response.getBody().getId());
      assertEquals(NOME, response.getBody().getNome());
      assertEquals(CPF, response.getBody().getCpf());
      assertEquals(EMAIL, response.getBody().getEmail());



  }

  @Test
  void whenDeleteWhitSuccess() {
    doNothing().when(service).delete(anyLong());
    ResponseEntity<Void> response = controller.delete(ID);

    assertNotNull(response);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());

    verify(service, times(1)).delete(anyLong());

  }

  private void startUsuario() {
    usuario = new Usuario(ID, NOME, CPF, EMAIL, null);
    optionalUsuario = Optional.of(new Usuario(ID, NOME, CPF, EMAIL, null));
    usuarioRequest = new UsuarioRequest(ID, NOME, CPF, EMAIL);
    usuarioResponse = new UsuarioResponse(ID, NOME, CPF, EMAIL);
  }
}
