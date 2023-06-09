package net.dg.stockfetcherservice.service;

import net.dg.stockfetcherservice.model.TwelveResponseBody;
import net.dg.stockfetcherservice.model.entity.StockData;

public interface TwelveStockService {

  TwelveResponseBody getStockInfo(String symbol);

  void fetchAndUpdateStockData(StockData stockData);
}
