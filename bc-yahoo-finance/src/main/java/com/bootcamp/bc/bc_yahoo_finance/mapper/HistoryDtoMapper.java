package com.bootcamp.bc.bc_yahoo_finance.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bootcamp.bc.bc_yahoo_finance.dto.HistoryDto;
import com.bootcamp.bc.bc_yahoo_finance.entity.HistoryEntity;

@Component
public class HistoryDtoMapper {

  @Autowired
  private ModelMapper modelMapper;

  public HistoryDto map(HistoryEntity historicalEntity) {
    return modelMapper.map(historicalEntity, HistoryDto.class);
  }
}
