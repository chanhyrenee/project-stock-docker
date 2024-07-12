package com.bootcamp.bc.bc_stock_web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StockPrice {
  private long timestamp;
  private double price;
  private double movingAverage10;
  private double movingAverage20;
  
}
