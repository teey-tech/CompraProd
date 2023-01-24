package br.com.app.cadprod.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.app.cadprod.model.request.UsuarioRequest;
import br.com.app.cadprod.model.response.UsuarioResponse;
import br.com.app.cadprod.persistence.entity.Usuario;

@Component
public class UsuarioMapper {

  @Autowired
  private ModelMapper mapper;

  public Usuario toUsuario(UsuarioRequest request) {
    return mapper.map(request, Usuario.class);
  }

  public UsuarioResponse toUsuarioResponse(Usuario usuario) {
    return mapper.map(usuario, UsuarioResponse.class);
  }

  public List<UsuarioResponse> toUsuarioResponseList(List<Usuario> usuarioList) {
    return usuarioList.stream()
        .map(this::toUsuarioResponse)
        .collect(Collectors.toList());

  }

}
