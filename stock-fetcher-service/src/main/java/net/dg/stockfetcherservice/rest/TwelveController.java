package net.dg.stockfetcherservice.rest;

import lombok.AllArgsConstructor;
import net.dg.stockfetcherservice.model.TwelveResponseBody;
import net.dg.stockfetcherservice.service.impl.TwelveStockServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TwelveController {

  private final TwelveStockServiceImpl twelveStockService;

  @GetMapping
  public TwelveResponseBody getStockInfo(@RequestParam String symbol) {
    return twelveStockService.getStockInfo(symbol);
  }
}
