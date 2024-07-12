package com.bootcamp.bc.bc_yahoo_finance.service;

import java.util.List;
import com.bootcamp.bc.bc_yahoo_finance.dto.HistoryDto;
import com.bootcamp.bc.bc_yahoo_finance.entity.HistoryEntity;
import com.bootcamp.bc.bc_yahoo_finance.model.ChartResponse;

public interface HistoryService {

  ChartResponse getHistoryApi(String symbol, String start, String end);

  void saveHistory(List<HistoryEntity> HistoricalEntity);

  List<HistoryDto> findAllHistory(String symbol);

  List<HistoryDto> findHistory(String symbol, String start,
      String end);

  void deleteHistory();
}
