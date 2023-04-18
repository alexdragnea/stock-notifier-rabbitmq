package net.dg.stockfetcherservice.service.impl;

import java.util.Optional;
import net.dg.stockfetcherservice.client.twelve.TwelveStockClient;
import net.dg.stockfetcherservice.model.TwelveResponseBody;
import net.dg.stockfetcherservice.model.entity.StockData;
import net.dg.stockfetcherservice.repository.StockDataRepository;
import net.dg.stockfetcherservice.service.StockDataConverterService;
import net.dg.stockfetcherservice.service.TwelveStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwelveStockServiceImpl implements TwelveStockService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TwelveStockServiceImpl.class);

  @Value("${interval}")
  private String interval;

  @Value("${outputSize}")
  private String outputSize;

  @Value("${apiKey}")
  private String apiKey;

  private final TwelveStockClient twelveStockClient;
  private final StockDataRepository stockDataRepository;
  private final StockDataConverterService stockDataConverterService;

  public TwelveStockServiceImpl(
      TwelveStockClient twelveStockClient,
      StockDataRepository stockDataRepository,
      StockDataConverterService stockDataConverterService) {
    this.twelveStockClient = twelveStockClient;
    this.stockDataRepository = stockDataRepository;
    this.stockDataConverterService = stockDataConverterService;
  }

  @Override
  public TwelveResponseBody getStockInfo(String symbol) {
    return twelveStockClient.getStockInfo(symbol, interval, outputSize, apiKey);
  }

  @Override
  public void saveStock(StockData stockData) {
    Optional<StockData> existingStockData = stockDataRepository.findBySymbol(stockData.getSymbol());
    existingStockData.ifPresent(stockDataRepository::save);
  }

  public void fetchAndUpdateStockData(StockData stockData) {
    TwelveResponseBody responseBody =
        twelveStockClient.getStockInfo(stockData.getSymbol(), interval, outputSize, apiKey);
    StockData existingStockData =
        stockDataConverterService.convertTwelveResponseToStockData(responseBody);
    stockData.setPrice(existingStockData.getPrice());
    stockData.setDateTime(existingStockData.getDateTime());
    stockData.setUpdatedAt(existingStockData.getUpdatedAt());
    LOGGER.info("TwelveStockService: updating stock: {}", stockData.getSymbol());
    stockDataRepository.save(stockData);
  }
}
