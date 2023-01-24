package br.com.app.cadprod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.app.cadprod.model.request.VendaRequest;
import br.com.app.cadprod.model.response.VendaResponse;
import br.com.app.cadprod.persistence.entity.Venda;

@Component
public class VendaMapper {

  @Autowired
  private ModelMapper mapper;

  public Venda toVenda(VendaRequest request) {
    return mapper.map(request, Venda.class);
  }

  public VendaResponse toVendaResponse(Venda venda) {
    return mapper.map(venda, VendaResponse.class);
  }
}
