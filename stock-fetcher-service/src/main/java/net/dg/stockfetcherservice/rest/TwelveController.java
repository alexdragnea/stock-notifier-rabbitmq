package net.dg.stockfetcherservice.rest;

import lombok.AllArgsConstructor;
import net.dg.stockfetcherservice.exceptions.TwelveApiException;
import net.dg.stockfetcherservice.model.TwelveResponseBody;
import net.dg.stockfetcherservice.model.entity.StockData;
import net.dg.stockfetcherservice.service.StockDataConverterService;
import net.dg.stockfetcherservice.service.TwelveValidationService;
import net.dg.stockfetcherservice.service.impl.TwelveStockServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TwelveController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TwelveController.class);

  private final TwelveStockServiceImpl twelveStockService;
  private final TwelveValidationService twelveValidationService;
  private final StockDataConverterService stockDataConverterService;

  @GetMapping
  public ResponseEntity<StockData> getStockInfo(@RequestParam String symbol) {

    try {
      TwelveResponseBody twelveResponseBody = twelveStockService.getStockInfo(symbol);
      twelveValidationService.validateApiResponse(twelveResponseBody);
      StockData stockData =
          stockDataConverterService.convertTwelveResponseToStockData(twelveResponseBody);
      twelveStockService.saveStock(stockData);
      return new ResponseEntity<>(stockData, HttpStatus.OK);
    } catch (TwelveApiException ex) {
      throw ex;
    }
  }
}
