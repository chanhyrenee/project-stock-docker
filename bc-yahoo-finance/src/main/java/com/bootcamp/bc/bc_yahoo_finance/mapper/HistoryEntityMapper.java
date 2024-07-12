package com.bootcamp.bc.bc_yahoo_finance.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Component;
import com.bootcamp.bc.bc_yahoo_finance.entity.HistoryEntity;
import com.bootcamp.bc.bc_yahoo_finance.model.ChartResponse;

@Component
public class HistoryEntityMapper {
  public List<HistoryEntity> map(ChartResponse chartResponse) {

    String symbol =
        chartResponse.getChart().getResult().get(0).getMeta().getSymbol();
    List<Long> timestamps =
        chartResponse.getChart().getResult().get(0).getTimestamp();
    List<Double> opens = chartResponse.getChart().getResult().get(0)
        .getIndicators().getQuote().get(0).getOpen();
    List<Double> closes = chartResponse.getChart().getResult().get(0)
        .getIndicators().getQuote().get(0).getClose();
    List<Double> lows = chartResponse.getChart().getResult().get(0)
        .getIndicators().getQuote().get(0).getLow();
    List<Double> highs = chartResponse.getChart().getResult().get(0)
        .getIndicators().getQuote().get(0).getHigh();
    List<Long> volumes = chartResponse.getChart().getResult().get(0)
        .getIndicators().getQuote().get(0).getVolume();

    int size = timestamps.size();
    if (opens.size() != size || closes.size() != size || lows.size() != size
        || highs.size() != size || volumes.size() != size) {
      throw new IllegalArgumentException("Yahoo historical data invalid.");
    }

    return IntStream.range(0, timestamps.size())
        .mapToObj(
            i -> new HistoryEntity(symbol, timestamps.get(i), opens.get(i),
                closes.get(i), lows.get(i), highs.get(i), volumes.get(i)))
        .collect(Collectors.toList()).stream()
        .filter(
            e -> e.getTimestamp() != 0l && e.getOpen() != 0.0 && e.getClose() != 0.0
                && e.getLow() != 0.0 && e.getHigh() != 0.0 && e.getVolume() != 0l)
        .collect(Collectors.toList());
  }
}
