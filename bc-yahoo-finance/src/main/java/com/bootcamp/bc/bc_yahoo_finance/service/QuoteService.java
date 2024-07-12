package com.bootcamp.bc.bc_yahoo_finance.service;

import com.bootcamp.bc.bc_yahoo_finance.dto.LatestQuoteDto;
import com.bootcamp.bc.bc_yahoo_finance.entity.QuoteEntity;

public interface QuoteService {

  QuoteEntity callApi(String symbol);

  void saveQuote(QuoteEntity quoteEntity);

  Long getMaxUnixTime(String symbol);

  void setSysDate();

  String getSysDate();

  LatestQuoteDto getLatestDateQuote(String symbol);

  void delete(String symbol);

  void deleteAll();
}
