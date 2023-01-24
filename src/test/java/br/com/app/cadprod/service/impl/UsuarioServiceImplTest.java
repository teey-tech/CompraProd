package br.com.app.cadprod.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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

import br.com.app.cadprod.exception.message.DataViolationException;
import br.com.app.cadprod.exception.message.ObjectNotFoundException;
import br.com.app.cadprod.model.request.UsuarioRequest;
import br.com.app.cadprod.model.response.UsuarioResponse;
import br.com.app.cadprod.persistence.entity.Usuario;
import br.com.app.cadprod.persistence.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceImplTest {

  @InjectMocks
  private UsuarioServiceImpl service;

  @Mock
  private UsuarioRepository repository;

  private Usuario usuario;

  private Optional<Usuario> optionalUsuario;

  private static final Long ID = (long) 1;
  private static final String NOME = "Teste";
  private static final String CPF = "69172763086";
  private static final String EMAIL = "teste@teste.com";
  private static final Integer INDEX = 0;

  private static final String USUARIO_NÃO_ENCONTRADO = "Usuario não encontrado";
  private static final String USUARIO_JÁ_CADASTRADO_NO_SISTEMA = "Produto já cadastrado";
  private static final String EMAIL_JA_CADASTRADO_NO_SISTEMA = "Email já cadastrado no sistema";

  private static final String CPF_JA_CADASTRADO_NO_SISTEMA = "CPF já cadastrado no sistema";

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    startUsuario();
  }

  @Test
  void whenCreateAndReturnSuccess(){
    when(repository.saveAndFlush(any())).thenReturn(usuario);
    Usuario response = service.create(usuario);

    assertNotNull(response);

    assertEquals(Usuario.class, response.getClass());
    assertEquals(NOME, response.getNome());
    assertEquals(CPF, response.getCpf());
    assertEquals(EMAIL, response.getEmail());

  }

  @Test
  void whenCreateAndReturnAEmailException(){
    when(repository.findByEmail(anyString())).thenReturn(optionalUsuario);
    try {
      optionalUsuario.get().setId((long) 2);
      service.create(usuario);
    } catch (Exception ex) {
      assertEquals(DataViolationException.class, ex.getClass());
      assertEquals(EMAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
    }
  }

  @Test
  void whenCreateAndReturnACPFException(){
    when(repository.findByCpf(anyString())).thenReturn(optionalUsuario);
    try {
      optionalUsuario.get().setId((long) 2);
      service.create(usuario);
    } catch (Exception ex) {
      assertEquals(DataViolationException.class, ex.getClass());
      assertEquals(CPF_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
    }
  }

  @Test
  void whenFindByIdThenReturnAnUsuarioInstance(){
    when(repository.findById(anyLong())).thenReturn(optionalUsuario);
    Usuario response = service.findById(ID);
    assertNotNull(response);


    assertEquals(Usuario.class, response.getClass());
    assertEquals(ID, response.getId());
    assertEquals(NOME, response.getNome());
    assertEquals(CPF, response.getCpf());
    assertEquals(EMAIL, response.getEmail());


  }

  @Test
  void whenFindByIdThenReturnAnObjectNotFoundException(){
    when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException(USUARIO_NÃO_ENCONTRADO));
    try {
      service.findById(ID);
    } catch (Exception ex) {
      assertEquals(ObjectNotFoundException.class, ex.getClass());
      assertEquals(USUARIO_NÃO_ENCONTRADO, ex.getMessage());
    }
  }

  @Test
  void whenFindAllThenReturnAListOfProduct(){
    when(repository.findAll()).thenReturn(List.of(usuario));
    List<Usuario> response = service.findAll();

    assertNotNull(response);
    
    assertEquals(Usuario.class, response.get(INDEX).getClass());
    assertEquals(1, response.size());
    assertEquals(ID, response.get(INDEX).getId());
    assertEquals(NOME, response.get(INDEX).getNome());
    assertEquals(CPF, response.get(INDEX).getCpf());
    assertEquals(EMAIL, response.get(INDEX).getEmail());


  }

  @Test
  void whenUpdateThenReturnSuccess(){
    when(repository.findById(anyLong())).thenReturn(optionalUsuario);
    when(repository.saveAndFlush(any())).thenReturn(usuario);
    
    Usuario response = service.update(ID, usuario);

    assertNotNull(response);

    assertEquals(Usuario.class, response.getClass());

    assertEquals(ID, response.getId());
    assertEquals(NOME, response.getNome());
    assertEquals(CPF, response.getCpf());
    assertEquals(EMAIL, response.getEmail());
  }

  @Test
  void whenUpdateThenReturnADataViolationException(){
    when(repository.findById(anyLong())).thenReturn(optionalUsuario);
    when(repository.saveAndFlush(any())).thenReturn(usuario);
    try {
      usuario.setId((long) 2);
      service.update(ID, usuario);
    } catch (Exception ex) {
      assertEquals(DataViolationException.class, ex.getClass() );
      assertEquals(USUARIO_JÁ_CADASTRADO_NO_SISTEMA, ex.getMessage());
    }
  }

  @Test
  void whenDeleteWithSucces(){
    when(repository.findById(anyLong())).thenReturn(optionalUsuario);
    doNothing().when(repository).deleteById(ID);
    service.delete(ID);
    verify(repository, times(1)).deleteById(anyLong());
  }

  @Test
  void whenDeleteReturnAObjectNotFoundException(){
    when(repository.findById(anyLong()))
      .thenThrow(new ObjectNotFoundException(USUARIO_NÃO_ENCONTRADO));
    
      try {
        service.delete(ID);
      } catch (Exception ex) {
          assertEquals(ObjectNotFoundException.class, ex.getClass());
          assertEquals(USUARIO_NÃO_ENCONTRADO, ex.getMessage());
      }
  }

  private void startUsuario() {
    usuario = new Usuario(ID, NOME, CPF, EMAIL, null);
    optionalUsuario = Optional.of(new Usuario(ID, NOME, CPF, EMAIL, null));
  }
}
