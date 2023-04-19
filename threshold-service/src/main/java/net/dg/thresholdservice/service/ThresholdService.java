package net.dg.thresholdservice.service;

import java.math.BigDecimal;
import net.dg.thresholdservice.entity.ThresholdData;

public interface ThresholdService {

  ThresholdData setThreshold(String email, String stockSymbol, BigDecimal threshold);

  void sendNotification();
}
