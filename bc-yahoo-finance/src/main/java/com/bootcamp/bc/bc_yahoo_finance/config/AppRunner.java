package com.bootcamp.bc.bc_yahoo_finance.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import com.bootcamp.bc.bc_yahoo_finance.entity.StockEntity;
import com.bootcamp.bc.bc_yahoo_finance.infra.RedisHelper;
import com.bootcamp.bc.bc_yahoo_finance.mapper.HistoryEntityMapper;
import com.bootcamp.bc.bc_yahoo_finance.service.HistoryService;
import com.bootcamp.bc.bc_yahoo_finance.service.StockListService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppRunner implements CommandLineRunner {

  private static final List<StockEntity> stockList = List.of(//
      new StockEntity("2800.HK", "Tracker Fund of Hong Kong"), //
      new StockEntity("0005.HK", "HSBC Holdings PLC"), //
      new StockEntity("0388.HK", "Hong Kong Exchanges and Clearing Limited"), //
      new StockEntity("0700.HK", "Tencent Holdings Limited"),
      new StockEntity("2388.HK", "BOC Hong Kong (Holdings) Limited"));

  @Autowired
  private RedisHelper redisHelper;

  @Autowired
  private StockListService stockListService;

  @Autowired
  private HistoryService historyService;

  @Autowired
  private HistoryEntityMapper historyEntityMapper;

  @Override
  public void run(String... args) throws Exception {
    redisHelper.clean();
    stockListService.setStockList(stockList);
    stockListService.deleteAll();
    stockListService.saveStockList(stockList);
    log.info("Stock list completed.");
    historyService.deleteHistory();
    try {
      stockListService.getStockList().getStockList().stream().forEach(stock -> {
        historyService.saveHistory(
            historyEntityMapper.map(historyService.getHistoryApi(stock,
                String.valueOf(1262275200), String.valueOf(1325347200))));
        historyService.saveHistory(
            historyEntityMapper.map(historyService.getHistoryApi(stock,
                String.valueOf(1325347200), String.valueOf(1388505600))));
        historyService.saveHistory(
            historyEntityMapper.map(historyService.getHistoryApi(stock,
                String.valueOf(1388505600), String.valueOf(1451577600))));
        historyService.saveHistory(
            historyEntityMapper.map(historyService.getHistoryApi(stock,
                String.valueOf(1451577600), String.valueOf(1514736000))));
        historyService.saveHistory(
            historyEntityMapper.map(historyService.getHistoryApi(stock,
                String.valueOf(1514736000), String.valueOf(1577808000))));
        // 2020-2024
        historyService.saveHistory(
            historyEntityMapper.map(historyService.getHistoryApi(stock,
                String.valueOf(1577808000), String.valueOf(1640966400))));
        historyService.saveHistory(
            historyEntityMapper.map(historyService.getHistoryApi(stock,
                String.valueOf(1640966400), String.valueOf(1704038400))));
        historyService.saveHistory(
            historyEntityMapper.map(historyService.getHistoryApi(stock,
                String.valueOf(1704038400), String.valueOf(1735660800))));
      });
    } catch (RestClientException e) {
      log.error("API connection error.");
    }
    log.info("CommandLineRunner completed, application start.");
  }
}
