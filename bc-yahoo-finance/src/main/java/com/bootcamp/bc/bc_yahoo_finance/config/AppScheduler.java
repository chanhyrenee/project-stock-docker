package com.bootcamp.bc.bc_yahoo_finance.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.bc.bc_yahoo_finance.infra.HKLocalDate;
import com.bootcamp.bc.bc_yahoo_finance.infra.RedisHelper;
import com.bootcamp.bc.bc_yahoo_finance.infra.TimeConverter;
import com.bootcamp.bc.bc_yahoo_finance.mapper.HistoryEntityMapper;
import com.bootcamp.bc.bc_yahoo_finance.service.HistoryService;
import com.bootcamp.bc.bc_yahoo_finance.service.QuoteService;
import com.bootcamp.bc.bc_yahoo_finance.service.StockListService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppScheduler {

  private static boolean holiday = false;

  @Autowired
  private StockListService stockListService;

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private HistoryService historyService;

  @Autowired
  private RedisHelper redisHelper;

  @Autowired
  private HistoryEntityMapper historyEntityMapper;

  @Value(value = "${spring.cache.redis.sysdate}")
  private String keySysDate;

  @Scheduled(cron = "0 25 9 ? * MON-FRI")
  @Async
  public void updateSysDate() {
    if (!holiday) {
      redisHelper.delete(keySysDate);
      quoteService.setSysDate();
    }
  }

  @Scheduled(cron = "0 0/5 9-16 * * MON-FRI")
  @Async
  public void callYahooApiQuote() throws InterruptedException {
    if (!holiday) {
      stockListService.getStockList().getStockList().stream().forEach(
          stock -> quoteService.saveQuote(quoteService.callApi(stock)));
      log.info("call api yahoo quote done at: " + LocalDateTime.now());
    }
  }

  @Scheduled(cron = "0 */5 19 ? * MON-FRI")
  @Async
  public void updateHistory() throws InterruptedException {
    if (!holiday) {
      stockListService.getStockList().getStockList().stream()
          .forEach(stock -> historyService.saveHistory(
              historyEntityMapper.map(historyService.getHistoryApi(stock,
                  String.valueOf(
                      TimeConverter.dateAndTime(LocalDateTime.now()) - 43200l),
                  String.valueOf(
                      TimeConverter.dateAndTime(LocalDateTime.now()))))));
      log.info("call api update daily history done at: " + LocalDateTime.now());
    }
  }

  @Scheduled(cron = "0 14 9 ? * MON-FRI")
  @Async
  public void daybegin() {
    if (!HKLocalDate.isPublicHoliday(LocalDate.now())) {
      holiday = false;
    }
  }

  @Scheduled(cron = "0 16 12 ? * MON-FRI")
  @Async
  public void noonBreakStart() {
    holiday = true;
  }

  @Scheduled(cron = "0 14 13 ? * MON-FRI")
  @Async
  public void noonBreakEnd() {
    if (!HKLocalDate.isPublicHoliday(LocalDate.now()))
      holiday = false;
  }

  @Scheduled(cron = "0 30 16 ? * MON-FRI")
  @Async
  public void dayend() {
    holiday = true;
  }
}
