package com.bootcamp.bc.bc_stock_web.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc.bc_stock_web.controller.StockPriceOperation;
import com.bootcamp.bc.bc_stock_web.dto.LatestAskBid;
import com.bootcamp.bc.bc_stock_web.dto.StockPrice;
import com.bootcamp.bc.bc_stock_web.mapper.StockPriceMapper;
import com.bootcamp.bc.bc_stock_web.service.DataService;

@RestController
public class StockPriceController implements StockPriceOperation {

  @Autowired
  private DataService dataService;

  @Autowired
  private StockPriceMapper stockPriceMapper;

  public List<String> getStockList(){
    return dataService.getStockList();
  }

  @Override
  public List<StockPrice> getFiveMinute(String symbol) {
    return stockPriceMapper.map(dataService.getSameDay5minsList(symbol));
  }

  @Override
  public LatestAskBid getLatest(String symbol) {
    return dataService.getAskBid(symbol);
  }

  public List<double[]> getHistoryData(String symbol) {
    return dataService.getHistoryData(symbol).stream()
        .map(d -> new double[] {d.getTimestamp() * 1000, d.getOpen(),
            d.getHigh(), d.getLow(), d.getClose(), d.getVolume()})
        .collect(Collectors.toList());
  }

}
