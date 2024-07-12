package com.bootcamp.bc.bc_yahoo_finance.mapper;

import java.time.Instant;
import org.springframework.stereotype.Component;
import com.bootcamp.bc.bc_yahoo_finance.entity.QuoteEntity;
import com.bootcamp.bc.bc_yahoo_finance.model.Interval;
import com.bootcamp.bc.bc_yahoo_finance.model.Quote;
import com.bootcamp.bc.bc_yahoo_finance.model.Quote.Result;

@Component
public class QuoteEntityMapper {

  public QuoteEntity map(Quote quote) {
    Result result = quote.getQuoteResponse().getResult().get(0);
    return QuoteEntity.builder()//
        .symbol(result.getSymbol())//
        .regularMarketTime(result.getRegularMarketTime())//
        .regularMarketPrice(result.getRegularMarketPrice())//
        .regularMarketChangePercent(result.getRegularMarketChangePercent())//
        .bid(result.getBid())//
        .bidSize(result.getBidSize())//
        .ask(result.getAsk())//
        .askSize(result.getAskSize())//
        .interval(Interval.MINS_5.getDesc())//
        .currentTimeStamp(Instant.now().getEpochSecond()).build();
  }

}
