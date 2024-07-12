package com.bootcamp.bc.bc_yahoo_finance.service;

import java.util.List;
import com.bootcamp.bc.bc_yahoo_finance.dto.StockListDto;
import com.bootcamp.bc.bc_yahoo_finance.entity.StockEntity;

public interface StockListService {

  void setStockList(List<StockEntity> stockList);

  void saveStockList(List<StockEntity> stockList);
  
  StockEntity getStockList(String symbol);

  StockListDto getStockList();

  String getCompanyName(String symbol);

  void deleteAll();
}
