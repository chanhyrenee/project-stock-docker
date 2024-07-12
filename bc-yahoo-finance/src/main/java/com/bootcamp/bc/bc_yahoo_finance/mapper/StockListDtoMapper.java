package com.bootcamp.bc.bc_yahoo_finance.mapper;

import java.util.Arrays;
import org.springframework.stereotype.Component;
import com.bootcamp.bc.bc_yahoo_finance.dto.StockListDto;

@Component
public class StockListDtoMapper {
  public StockListDto map(String[] list) {
    return StockListDto.builder().stockList(Arrays.asList(list)).build();
  }
}
