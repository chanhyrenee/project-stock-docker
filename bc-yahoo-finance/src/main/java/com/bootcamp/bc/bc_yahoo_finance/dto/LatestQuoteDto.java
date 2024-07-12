package com.bootcamp.bc.bc_yahoo_finance.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LatestQuoteDto {
  private LatestQuote latestQuote;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
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
    @EqualsAndHashCode
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
