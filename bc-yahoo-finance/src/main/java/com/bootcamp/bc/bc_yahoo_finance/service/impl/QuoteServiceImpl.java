package com.bootcamp.bc.bc_yahoo_finance.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc.bc_yahoo_finance.dto.LatestQuoteDto;
import com.bootcamp.bc.bc_yahoo_finance.entity.QuoteEntity;
import com.bootcamp.bc.bc_yahoo_finance.infra.Protocol;
import com.bootcamp.bc.bc_yahoo_finance.infra.RedisHelper;
import com.bootcamp.bc.bc_yahoo_finance.infra.TimeConverter;
import com.bootcamp.bc.bc_yahoo_finance.infra.UrlBuilder;
import com.bootcamp.bc.bc_yahoo_finance.infra.YahooAccess;
import com.bootcamp.bc.bc_yahoo_finance.mapper.LatestQuoteDtoMapper;
import com.bootcamp.bc.bc_yahoo_finance.mapper.QuoteEntityMapper;
import com.bootcamp.bc.bc_yahoo_finance.model.Quote;
import com.bootcamp.bc.bc_yahoo_finance.repository.QuoteRepository;
import com.bootcamp.bc.bc_yahoo_finance.service.QuoteService;
import com.bootcamp.bc.bc_yahoo_finance.service.StockListService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuoteServiceImpl implements QuoteService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RedisHelper redisHelper;

  @Autowired
  private StockListService stockListService;

  @Autowired
  private QuoteRepository quoteRepository;

  @Autowired
  private QuoteEntityMapper quoteEntityMapper;

  @Autowired
  private LatestQuoteDtoMapper latestQuoteDtoMapper;

  @Value(value = "${api.yahoofinanceapi.domain}")
  private String domain;

  @Value(value = "${api.yahoofinanceapi.endpoint.v7}")
  private String quoteEndpoint;

  @Value(value = "${spring.cache.redis.5minQuote}")
  private String key5minQuote;

  @Value(value = "${spring.cache.redis.stock-list}")
  private String keyStockList;

  @Value(value = "${spring.cache.redis.sysdate}")
  private String keySysDate;

  @Value(value = "${spring.cache.redis.AskBid}")
  private String keyAskBid;

  @Override
  public QuoteEntity callApi(String symbol) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cookie", YahooAccess.getCookie());
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>() {
      {
        add("symbols", symbol);
        add("crumb", YahooAccess.getCrumb());
      }
    };
    String url = UrlBuilder.get(Protocol.HTTPS, domain, quoteEndpoint, params);
    return quoteEntityMapper.map(restTemplate
        .exchange(url, HttpMethod.GET, httpEntity, Quote.class).getBody());
  }

  @Override
  public void saveQuote(QuoteEntity quoteEntity) {
    quoteRepository.save(quoteEntity);
  }

  @Override
  public Long getMaxUnixTime(String symbol) {
    return quoteRepository.findMaxUnixTime(symbol);
  };

  @Override
  public void setSysDate() {
    redisHelper.set(keySysDate, TimeConverter.date(LocalDate.now()));
  }

  @Override
  public String getSysDate() {
    String date1 = redisHelper.get(keySysDate, String.class);
    if (date1 != null) {
      return date1;
    }
    log.debug("redis system date missing.");
    String date2 = TimeConverter.date(quoteRepository.findMaxUnixTime());
    redisHelper.set(keySysDate, date2);
    return date2;
  }

  @Override
  public LatestQuoteDto getLatestDateQuote(String symbol) {
    Long unixTime = getMaxUnixTime(symbol);
    LatestQuoteDto response1 =
        redisHelper.get(key5minQuote + symbol, LatestQuoteDto.class);
    if (response1 != null
        && response1.getLatestQuote().getRegularMarketTime() == unixTime)
      return response1;
    LatestQuoteDto response2 =
        latestQuoteDtoMapper.map(stockListService.getCompanyName(symbol),
            unixTime, quoteRepository.findAllByDate(getSysDate(), symbol));
    redisHelper.set(key5minQuote + symbol, response2, Duration.ofHours(12));
    return response2;
  }

  @Override
  public void delete(String symbol) {
    redisHelper.delete(key5minQuote + symbol);
  }

  @Override
  public void deleteAll() {
    quoteRepository.deleteAll();
  }

}
