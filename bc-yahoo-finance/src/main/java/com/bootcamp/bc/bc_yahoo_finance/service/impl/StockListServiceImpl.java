package com.bootcamp.bc.bc_yahoo_finance.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bootcamp.bc.bc_yahoo_finance.dto.StockListDto;
import com.bootcamp.bc.bc_yahoo_finance.entity.StockEntity;
import com.bootcamp.bc.bc_yahoo_finance.infra.RedisHelper;
import com.bootcamp.bc.bc_yahoo_finance.mapper.StockListDtoMapper;
import com.bootcamp.bc.bc_yahoo_finance.repository.StockRepository;
import com.bootcamp.bc.bc_yahoo_finance.service.StockListService;

@Service
public class StockListServiceImpl implements StockListService {

  @Autowired
  private RedisHelper redisHelper;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private StockListDtoMapper stockListDtoMapper;

  @Value(value = "${spring.cache.redis.stockList}")
  private String keyStockList;

  @Override
  public void setStockList(List<StockEntity> stockList) {
    redisHelper.set(keyStockList, stockList.stream()
        .map(stock -> stock.getStockSymbol()).collect(Collectors.toList()),
        Duration.ofDays(1l));
  }

  @Override
  public void saveStockList(List<StockEntity> stockList) {
    stockRepository.saveAll(stockList);
  }

  @Override
  public StockEntity getStockList(String symbol) {
    return stockRepository.findByStockSymbol(symbol);
  }

  @Override
  public StockListDto getStockList() {
    String[] response = redisHelper.get(keyStockList, String[].class);
    if (response != null) {
      return stockListDtoMapper.map(response);
    }
    List<StockEntity> dbList = stockRepository.findAll();
    setStockList(dbList);
    return stockListDtoMapper
        .map(redisHelper.get(keyStockList, String[].class));
  }

  @Override
  public String getCompanyName(String symbol){
    return stockRepository.findByStockSymbol(symbol).getCompanyName();
  }

  @Override
  public void deleteAll() {
    stockRepository.deleteAll();
  }

}
