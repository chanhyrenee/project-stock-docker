package com.bootcamp.bc.bc_stock_web.service;

import java.util.List;
import com.bootcamp.bc.bc_stock_web.dto.LatestAskBid;
import com.bootcamp.bc.bc_stock_web.model.History;
import com.bootcamp.bc.bc_stock_web.model.Quote;

public interface DataService {

  List<String> getStockList();

  Quote getSameDay5minsList(String symbol);

  LatestAskBid getAskBid(String symbol);

  List<History> getHistoryData(String symbol);
}
