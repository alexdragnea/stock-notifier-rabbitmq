package net.dg.stockfetcherservice.scheduler;

import java.util.List;
import lombok.AllArgsConstructor;
import net.dg.stockfetcherservice.model.entity.StockData;
import net.dg.stockfetcherservice.repository.StockDataRepository;
import net.dg.stockfetcherservice.service.impl.TwelveStockServiceImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@AllArgsConstructor
public class StockUpdateScheduler {

  private final TwelveStockServiceImpl twelveStockService;
  private final StockDataRepository stockDataRepository;

  @Scheduled(fixedRate = 300000) // run every 5 minutes
  public void updateStockData() {
    List<StockData> stocks = stockDataRepository.findAll();
    for (StockData stock : stocks) {
      twelveStockService.fetchAndUpdateStockData(stock);
    }
  }
}
