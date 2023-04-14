package net.dg.stockfetcherservice.service.impl;

import net.dg.stockfetcherservice.client.twelve.TwelveStockClient;
import net.dg.stockfetcherservice.model.TwelveResponseBody;
import net.dg.stockfetcherservice.service.TwelveStockService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwelveStockServiceImpl implements TwelveStockService {

  @Value("${outputSize}")
  private String outputSize;

  @Value("${apiKey}")
  private String apiKey;

  private final TwelveStockClient twelveStockClient;

  public TwelveStockServiceImpl(TwelveStockClient twelveStockClient) {
    this.twelveStockClient = twelveStockClient;
  }

  @Override
  public TwelveResponseBody getStockInfo(String symbol) {
    return twelveStockClient.getStockInfo(symbol, outputSize, apiKey);
  }
}
