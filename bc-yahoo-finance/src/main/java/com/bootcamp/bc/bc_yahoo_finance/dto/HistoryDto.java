package com.bootcamp.bc.bc_yahoo_finance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HistoryDto {

  private Long timestamp;
  private Double close;
  private Double low;
  private Double high;
  private Double open;
  private Long volume;

}
