package net.dg.thresholdservice.service;

import net.dg.thresholdservice.entity.TresholdData;

import java.math.BigDecimal;

public interface ThresholdService {

  TresholdData setThreshold(String email, String stockSymbol, BigDecimal threshold);
}
