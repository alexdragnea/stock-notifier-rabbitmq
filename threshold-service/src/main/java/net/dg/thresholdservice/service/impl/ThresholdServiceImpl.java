package net.dg.thresholdservice.service.impl;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import net.dg.thresholdservice.client.stockfetcher.StockFetcherClient;
import net.dg.thresholdservice.entity.StockData;
import net.dg.thresholdservice.entity.TresholdData;
import net.dg.thresholdservice.repository.ThresholdDataRepository;
import net.dg.thresholdservice.service.ThresholdService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ThresholdServiceImpl implements ThresholdService {

  private final ThresholdDataRepository thresholdDataRepository;
  private final StockFetcherClient stockFetcherClient;

  public TresholdData setThreshold(String email, String stockSymbol, BigDecimal threshold) {
    StockData stockData = stockFetcherClient.getStockData(stockSymbol);
    TresholdData tresholdData = new TresholdData();
    tresholdData.setEmail(email);
    tresholdData.setStockData(stockData);
    tresholdData.setThreshold(threshold);
    thresholdDataRepository.save(tresholdData);
    return tresholdData;
  }
}
