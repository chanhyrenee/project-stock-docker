package com.bootcamp.bc.bc_yahoo_finance.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tstocks")
@Getter
@NoArgsConstructor
public class StockEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String stockSymbol;
  private String companyName;

  public StockEntity(String stockSymbol, String companyName) {
    this.stockSymbol = stockSymbol;
    this.companyName = companyName;
  }
}
