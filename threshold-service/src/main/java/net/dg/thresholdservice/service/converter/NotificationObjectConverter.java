package net.dg.thresholdservice.service.converter;

import net.dg.notificationservice.model.Notification;
import net.dg.thresholdservice.entity.StockData;
import net.dg.thresholdservice.entity.ThresholdData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationObjectConverter {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationObjectConverter.class);

  public Notification convertThresholdDataToNotification(ThresholdData thresholdData) {

    LOGGER.info("Converting ThresholdData object: {} to Notification object.", thresholdData);

    StockData stockData = thresholdData.getStockData();

    return Notification.builder()
        .email(thresholdData.getEmail())
        .symbol(stockData.getSymbol())
        .currency(stockData.getCurrency())
        .threshold(thresholdData.getThreshold())
        .price(stockData.getPrice())
        .updatedAt(stockData.getUpdatedAt())
        .build();
  }
}
