package com.bootcamp.bc.bc_yahoo_finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.bc.bc_yahoo_finance.entity.StockEntity;


@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
  StockEntity findByStockSymbol(String stockSymbol);
}
