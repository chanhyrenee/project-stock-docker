package com.bootcamp.bc.bc_stock_web.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Quote {
  private LatestQuote latestQuote;

  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Getter
  public static class LatestQuote {

    private String companyName;
    private Long regularMarketTime;
    private Double ask;
    private Double bid;
    private Double regularMarketChangePercent;
    private List<Data> data;

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class Data {
      private String symbol;
      private String marketTime;
      private Long regularMarketUnix;
      private Double regularMarketPrice;
      private Double bid;
      private Double ask;
    }
  }
}
