package com.bootcamp.bc.bc_yahoo_finance.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.bootcamp.bc.bc_yahoo_finance.dto.LatestQuoteDto;
import com.bootcamp.bc.bc_yahoo_finance.dto.LatestQuoteDto.LatestQuote;
import com.bootcamp.bc.bc_yahoo_finance.dto.LatestQuoteDto.LatestQuote.Data;
import com.bootcamp.bc.bc_yahoo_finance.entity.QuoteEntity;
import com.bootcamp.bc.bc_yahoo_finance.infra.TimeConverter;

@Component
public class LatestQuoteDtoMapper {

  public LatestQuoteDto map(String companyName, Long mktTime,
      List<QuoteEntity> quoteEntities) {

    List<Data> dataList = new ArrayList<>();
    quoteEntities.stream().forEach(q -> {
      dataList.add(Data.builder()//
          .symbol(q.getSymbol())//
          .marketTime(TimeConverter.dateAndTime(q.getCurrentTimeStamp()))//
          .regularMarketUnix(q.getRegularMarketTime())//
          .regularMarketPrice(q.getRegularMarketPrice())//
          .bid(q.getBid())//
          .ask(q.getAsk()).build());
    });

    QuoteEntity quoteEntity = quoteEntities.stream().max((q1, q2) -> Long
        .compare(q1.getRegularMarketTime(), q2.getRegularMarketTime()))
        .orElse(quoteEntities.get(0));

    return LatestQuoteDto.builder().latestQuote(LatestQuote.builder()
        .companyName(companyName).ask(quoteEntity.getAsk())
        .bid(quoteEntity.getBid())
        .regularMarketChangePercent(quoteEntity.getRegularMarketChangePercent())
        .regularMarketTime(mktTime).data(dataList).build()).build();
  }

}
