package com.bootcamp.bc.bc_stock_web.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;
import com.bootcamp.bc.bc_stock_web.dto.StockPrice;
import com.bootcamp.bc.bc_stock_web.model.Quote;
import com.bootcamp.bc.bc_stock_web.model.Quote.LatestQuote.Data;

@Component
public class StockPriceMapper {

  private final static int ma10Period = 10;
  private final static int ma20Period = 20;

  public List<StockPrice> map(Quote quote) {
    List<StockPrice> list = new ArrayList<>();
    List<Double> movingAverages10 =
        calculateMovingAverage(quote.getLatestQuote().getData(), ma10Period);
    List<Double> movingAverages20 =
        calculateMovingAverage(quote.getLatestQuote().getData(), ma20Period);
    AtomicInteger i = new AtomicInteger(0);
    AtomicInteger j = new AtomicInteger(0);
    quote.getLatestQuote().getData().stream().forEach(data -> {//
      StockPrice stockPrice = new StockPrice((//
      data.getRegularMarketUnix() + 28800L) * 1000, //
          data.getRegularMarketPrice(), //
          movingAverages10.get(i.getAndIncrement()),
          movingAverages20.get(j.getAndIncrement()));
      list.add(stockPrice);
    });
    return list;
  }

  private List<Double> calculateMovingAverage(List<Data> data, int period) {
    List<Double> movingAverages = new ArrayList<>();
    for (int i = 0; i < data.size(); i++) {
      if (i < period - 1) {
        movingAverages.add(0.0);
      } else {
        double sum = 0.0;
        for (int j = i; j > i - period; j--) {
          sum += data.get(j).getRegularMarketPrice();
        }
        movingAverages.add(sum / period);
      }
    }
    return movingAverages;
  }
}
