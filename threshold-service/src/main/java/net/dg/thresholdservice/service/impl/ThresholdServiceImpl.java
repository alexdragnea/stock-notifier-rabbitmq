package net.dg.thresholdservice.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import net.dg.thresholdservice.client.stockfetcher.StockFetcherClient;
import net.dg.thresholdservice.entity.StockData;
import net.dg.thresholdservice.entity.ThresholdData;
import net.dg.thresholdservice.repository.StockDataRepository;
import net.dg.thresholdservice.repository.ThresholdDataRepository;
import net.dg.thresholdservice.service.ThresholdService;
import net.dg.thresholdservice.service.converter.NotificationObjectConverter;
import net.dg.thresholdservice.service.rabbitmq.NotificationProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ThresholdServiceImpl implements ThresholdService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ThresholdServiceImpl.class);

  private final ThresholdDataRepository thresholdDataRepository;
  private final StockFetcherClient stockFetcherClient;
  private final NotificationProducer notificationProducer;
  private final NotificationObjectConverter notificationObjectConverter;
  private final StockDataRepository stockDataRepository;

  @Override
  public ThresholdData setThreshold(String email, String stockSymbol, BigDecimal threshold) {
    StockData stockData = stockFetcherClient.getStockData(stockSymbol);

    Optional<StockData> existingStockData = stockDataRepository.findBySymbol(stockSymbol);
    if (existingStockData.isEmpty()) {
      stockDataRepository.save(stockData);
    } else {
      stockData = existingStockData.get();
    }

    ThresholdData thresholdData = new ThresholdData();
    thresholdData.setEmail(email);
    thresholdData.setStockData(stockData);
    thresholdData.setThreshold(threshold);
    thresholdDataRepository.save(thresholdData);

    return thresholdData;
  }

  @Override
  @Scheduled(fixedRate = 300000) // 5 minutes
  public void sendNotification() {

    List<ThresholdData> thresholdDataList = thresholdDataRepository.findAll();
    for (ThresholdData thresholdData : thresholdDataList) {
      BigDecimal threshold = thresholdData.getThreshold();
      BigDecimal stockPrice = thresholdData.getStockData().getPrice();
      if (threshold.compareTo(stockPrice) <= 0) {
        LOGGER.info(
            "Sending notification for threshold data with id: {} and symbol: {}",
            thresholdData.getId(),
            thresholdData.getStockData().getSymbol());
        notificationProducer.sendMessage(
            notificationObjectConverter.convertThresholdDataToNotification(thresholdData));
      }
    }
  }
}
