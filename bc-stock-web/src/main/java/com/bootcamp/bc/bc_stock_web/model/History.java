package com.bootcamp.bc.bc_stock_web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class History {

  private Long timestamp;
  private Double open;
  private Double high;
  private Double low;
  private Double close;
  private Long volume;
}
