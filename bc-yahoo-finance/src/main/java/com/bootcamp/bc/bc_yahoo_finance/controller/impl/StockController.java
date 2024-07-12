package com.bootcamp.bc.bc_yahoo_finance.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc.bc_yahoo_finance.controller.StockOperation;
import com.bootcamp.bc.bc_yahoo_finance.dto.HistoryDto;
import com.bootcamp.bc.bc_yahoo_finance.dto.LatestQuoteDto;
import com.bootcamp.bc.bc_yahoo_finance.dto.StockListDto;
import com.bootcamp.bc.bc_yahoo_finance.infra.ApiResp;
import com.bootcamp.bc.bc_yahoo_finance.infra.TimeConverter;
import com.bootcamp.bc.bc_yahoo_finance.mapper.HistoryEntityMapper;
import com.bootcamp.bc.bc_yahoo_finance.service.HistoryService;
import com.bootcamp.bc.bc_yahoo_finance.service.QuoteService;
import com.bootcamp.bc.bc_yahoo_finance.service.StockListService;

@RestController
public class StockController implements StockOperation {

  @Autowired
  private StockListService stockListService;

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private HistoryService historyService;

  @Autowired
  private HistoryEntityMapper historyEntityMapper;

  @Override
  public ApiResp<StockListDto> getStockList() {
    return ApiResp.<StockListDto>builder() //
        .success() //
        .data(stockListService.getStockList()) //
        .build();
  }

  @Override
  public ApiResp<String> getSysDate(String symbol) {
    return ApiResp.<String>builder() //
    .success() //
    .data(TimeConverter.date(quoteService.getMaxUnixTime(symbol))) //
    .build();
  }

  @Override
  public ApiResp<LatestQuoteDto> getLatestDateQuote(String symbol) {
    return ApiResp.<LatestQuoteDto>builder() //
    .success() //
    .data(quoteService.getLatestDateQuote(symbol)) //
    .build();
  }

  @Override
  public void manualcallapi(String symbol) {
    quoteService.saveQuote(quoteService.callApi(symbol));
  }
  
  @Override
  public ApiResp<List<HistoryDto>> getHistory(String symbol, String start, String end) {
    return ApiResp.<List<HistoryDto>>builder() //
    .success() //
    .data(historyService.findHistory(symbol, start, end)) //
    .build();
  }

  @Override
  public ApiResp<List<HistoryDto>> getAllHistory(@RequestParam String symbol) {
    return ApiResp.<List<HistoryDto>>builder() //
    .success() //
    .data(historyService.findAllHistory(symbol)) //
    .build();
  }

  @Override
  public void saveHistory(String symbol, String start, String end) {
    historyService.saveHistory(historyEntityMapper
        .map(historyService.getHistoryApi(symbol, start, end)));
  }

  @Override
  public void deleteHistory() {
    historyService.deleteHistory();
  }

}
