package com.bootcamp.bc.bc_stock_web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LatestAskBid {
  private String companyName;
  private Double ask;
  private Double bid;
  private Double regularMarketChangePercent;
}
