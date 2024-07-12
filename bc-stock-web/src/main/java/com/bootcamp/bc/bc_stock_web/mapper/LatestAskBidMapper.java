package com.bootcamp.bc.bc_stock_web.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.bc.bc_stock_web.dto.LatestAskBid;
import com.bootcamp.bc.bc_stock_web.model.Quote;

@Component
public class LatestAskBidMapper {
  public LatestAskBid map(Quote quote) {
    return LatestAskBid.builder()
        .companyName(quote.getLatestQuote().getCompanyName())
        .ask(Math.round(quote.getLatestQuote().getAsk() * 100.0) / 100.0)
        .bid(Math.round(quote.getLatestQuote().getBid() * 100.0) / 100.0)
        .regularMarketChangePercent(Math.round(
            quote.getLatestQuote().getRegularMarketChangePercent() * 100.0)
            / 100.0)
        .build();

  }
}
