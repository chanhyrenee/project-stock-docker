package com.bootcamp.bc.bc_yahoo_finance.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockListDto {
  private List<String> stockList;
}
