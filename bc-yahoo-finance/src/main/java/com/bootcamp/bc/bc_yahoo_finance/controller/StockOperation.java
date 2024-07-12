package com.bootcamp.bc.bc_yahoo_finance.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.bc.bc_yahoo_finance.dto.HistoryDto;
import com.bootcamp.bc.bc_yahoo_finance.dto.LatestQuoteDto;
import com.bootcamp.bc.bc_yahoo_finance.dto.StockListDto;
import com.bootcamp.bc.bc_yahoo_finance.infra.ApiResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface StockOperation {

  @Operation(summary = "Get All Stocks",
      description = "This endpoint is to get all stocks")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = {@Content(schema = @Schema(implementation = ApiResp.class),
              mediaType = "application/json")}),
      @ApiResponse(responseCode = "500",
          content = {@Content(schema = @Schema())})})
  @ResponseStatus(value = HttpStatus.OK)
  @GetMapping(value = "/stocklist")
  ApiResp<StockListDto> getStockList();

  @GetMapping(value = "/sysdate")
  ApiResp<String> getSysDate(@RequestParam String symbol);

  @GetMapping(value = "/stock5minquote")
  ApiResp<LatestQuoteDto> getLatestDateQuote(@RequestParam String symbol);

  @PostMapping(value = "/stock5minquote")
  void manualcallapi(@RequestParam String symbol);

  @GetMapping(value = "/history/{start}-{end}/{symbol}")
  ApiResp<List<HistoryDto>> getHistory(@PathVariable String start,
      @PathVariable String end, @PathVariable String symbol);

  @GetMapping(value = "/history")
  ApiResp<List<HistoryDto>> getAllHistory(@RequestParam String symbol);

  @PostMapping(value = "/history")
  void saveHistory(@RequestParam String symbol, @RequestParam String start,
      @RequestParam String end);

  @DeleteMapping(value = "/history")
  void deleteHistory();
}
