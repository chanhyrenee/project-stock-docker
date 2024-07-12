package com.bootcamp.bc.bc_yahoo_finance.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tstock_quote_yahoo")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class QuoteEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  private Long regularMarketTime;
  private Double regularMarketPrice;
  private Double regularMarketChangePercent;
  private Double bid;
  private Integer bidSize;
  private Double ask;
  private Integer askSize;
  private String interval;
  private Long currentTimeStamp;

}
