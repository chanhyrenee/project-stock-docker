package com.bootcamp.bc.bc_yahoo_finance.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc.bc_yahoo_finance.dto.HistoryDto;
import com.bootcamp.bc.bc_yahoo_finance.entity.HistoryEntity;
import com.bootcamp.bc.bc_yahoo_finance.infra.Protocol;
import com.bootcamp.bc.bc_yahoo_finance.infra.UrlBuilder;
import com.bootcamp.bc.bc_yahoo_finance.mapper.HistoryDtoMapper;
import com.bootcamp.bc.bc_yahoo_finance.model.ChartResponse;
import com.bootcamp.bc.bc_yahoo_finance.repository.HistoryRepository;
import com.bootcamp.bc.bc_yahoo_finance.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HistoryRepository historyRepository;

  @Autowired
  private HistoryDtoMapper historyDtoMapper;

  @Value(value = "${api.yahoofinanceapi.domain}")
  private String domain;

  @Value(value = "${api.yahoofinanceapi.endpoint.v8}")
  private String historyEndpoint;

  @Override
  public ChartResponse getHistoryApi(String symbol, String start, String end) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>() {
      {
        add("period1", start);
        add("period2", end);
        add("interval", "1d");
        add("events", "history");
      }
    };
    return restTemplate.getForObject(
        UrlBuilder.get(Protocol.HTTPS, domain, historyEndpoint, params, symbol),
        ChartResponse.class);
  }

  @Override
  public void saveHistory(List<HistoryEntity> HistoricalEntity) {
    historyRepository.saveAll(HistoricalEntity);
  }

  @Override
  public List<HistoryDto> findAllHistory(String symbol) {
    return historyRepository
        .findBySymbol(symbol).stream()
        .map(h -> historyDtoMapper.map(h)).collect(Collectors.toList());
  }

  @Override
  public List<HistoryDto> findHistory(String symbol, String start, String end) {
    return historyRepository
        .findByRange(symbol, Long.valueOf(start), Long.valueOf(end)).stream()
        .map(h -> historyDtoMapper.map(h)).collect(Collectors.toList());
  }

  @Override
  public void deleteHistory() {
    historyRepository.deleteAll();
  }

}
