package com.bootcamp.bc.bc_stock_web.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc.bc_stock_web.dto.LatestAskBid;
import com.bootcamp.bc.bc_stock_web.infra.ApiResp;
import com.bootcamp.bc.bc_stock_web.infra.Protocol;
import com.bootcamp.bc.bc_stock_web.infra.UrlBuilder;
import com.bootcamp.bc.bc_stock_web.mapper.LatestAskBidMapper;
import com.bootcamp.bc.bc_stock_web.model.History;
import com.bootcamp.bc.bc_stock_web.model.Quote;
import com.bootcamp.bc.bc_stock_web.model.StockList;
import com.bootcamp.bc.bc_stock_web.service.DataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataServiceImpl implements DataService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private LatestAskBidMapper latestAskBidMapper;

  @Value(value = "${api.stockprice5min.domain}")
  private String domain;

  @Value(value = "${api.stockprice5min.endstocklist}")
  private String endpointstocklist;

  @Value(value = "${api.stockprice5min.endpoint5min}")
  private String endpoint5min;

  @Value(value = "${api.stockprice5min.endpointHistory}")
  private String endpointHistory;

  @Override
  public List<String> getStockList() {
    String url = UrlBuilder.get(Protocol.HTTP, domain, endpointstocklist);
    ApiResp<StockList> response =
        restTemplate
            .exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<ApiResp<StockList>>() {})
            .getBody();
    return response != null ? response.getData().getStockList() : null;
  }

  @Override
  public Quote getSameDay5minsList(String symbol) {
    String url =
        UrlBuilder.get(Protocol.HTTP, domain, endpoint5min, "symbol", symbol);
        ApiResp<Quote> response =
        restTemplate
            .exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<ApiResp<Quote>>() {})
            .getBody();
    return response != null ? response.getData() : null;
  }

  @Override
  public LatestAskBid getAskBid(String symbol) {
    String url =
        UrlBuilder.get(Protocol.HTTP, domain, endpoint5min, "symbol", symbol);
        ApiResp<Quote> response =
        restTemplate
            .exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<ApiResp<Quote>>() {})
            .getBody();
    return response != null ? latestAskBidMapper.map(response.getData()) : null;
  }

  @Override
  public List<History> getHistoryData(String symbol) {
    String url = UrlBuilder.get(Protocol.HTTP, domain, endpointHistory,
        "symbol", symbol);

        ApiResp<List<History>> response =
        restTemplate
            .exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<ApiResp<List<History>>>() {})
            .getBody();
    return response != null ? response.getData() : null;
  }

}
