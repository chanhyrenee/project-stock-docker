package com.bootcamp.bc.bc_stock_web.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc.bc_stock_web.dto.LatestAskBid;
import com.bootcamp.bc.bc_stock_web.dto.StockPrice;

public interface StockPriceOperation {

  @GetMapping(value = "/stocklist")
  List<String> getStockList();

  @GetMapping(value = "/five-minute")
  List<StockPrice> getFiveMinute(@RequestParam String symbol);

  @GetMapping(value = "/latest")
  LatestAskBid getLatest(@RequestParam String symbol);

  @GetMapping(value = "/sma-candle")
  List<double[]> getHistoryData(@RequestParam String symbol);

}
