package br.com.app.cadprod.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.app.cadprod.model.response.ItemVendaResponse;
import br.com.app.cadprod.persistence.entity.ItemVenda;

@Component
public class ItemVendaMapper {

  @Autowired
  private ModelMapper mapper;

  public ItemVendaResponse toItemVendaResponse(ItemVenda itemVenda) {
    return mapper.map(itemVenda, ItemVendaResponse.class);
  }

  public List<ItemVendaResponse> toItemVendaResponseList(List<ItemVenda> itemVendaList) {
    return itemVendaList.stream()
        .map(this::toItemVendaResponse)
        .collect(Collectors.toList());

  }
}
