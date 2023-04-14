package net.dg.stockfetcherservice.service;

import net.dg.stockfetcherservice.model.TwelveResponseBody;

public interface TwelveStockService {

  TwelveResponseBody getStockInfo(String symbol);
}
