package com.bootcamp.bc.bc_yahoo_finance.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tstock_history_yahoo")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class HistoryEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  private Long timestamp;
  private Double open;
  private Double close;
  private Double low;
  private Double high;
  private Long volume;

  public HistoryEntity(String symbol, Long timestamp, Double open, Double close,
      Double low, Double high, Long volume) {
    this.symbol = symbol;
    this.timestamp = timestamp;
    this.open = open;
    this.close = close;
    this.low = low;
    this.high = high;
    this.volume = volume;
  }
}
