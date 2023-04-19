package net.dg.thresholdservice.rest;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import net.dg.thresholdservice.entity.ThresholdData;
import net.dg.thresholdservice.service.converter.NotificationObjectConverter;
import net.dg.thresholdservice.service.impl.ThresholdServiceImpl;
import net.dg.thresholdservice.service.rabbitmq.NotificationProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threshold")
@AllArgsConstructor
public class ThresholdController {

  private ThresholdServiceImpl tresholdService;
  private NotificationProducer notificationProducer;
  private NotificationObjectConverter notificationObjectConverter;

  @PostMapping("/set")
  public ResponseEntity<ThresholdData> setThreshold(
      @RequestParam String email,
      @RequestParam String stockSymbol,
      @RequestParam BigDecimal threshold) {
    ThresholdData thresholdData = tresholdService.setThreshold(email, stockSymbol, threshold);

    return new ResponseEntity<>(thresholdData, HttpStatus.OK);
  }
}
