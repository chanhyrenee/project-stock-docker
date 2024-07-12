package com.bootcamp.bc.bc_yahoo_finance.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Quote {
  private QuoteResponse quoteResponse;

  @Data
  @NoArgsConstructor
  public static class QuoteResponse {
    private List<Result> result;
    private Error error;
  }

  @Data
  @NoArgsConstructor
  public static class Result {
    private String symbol;
    private Long regularMarketTime;
    private Double regularMarketPrice;
    private Double regularMarketChangePercent;
    private Double bid;
    private Integer bidSize;
    private Double ask;
    private Integer askSize;
  }

  @Data
  @NoArgsConstructor
  public static class Error {
    private String code;
    private String desc;
  }
}
