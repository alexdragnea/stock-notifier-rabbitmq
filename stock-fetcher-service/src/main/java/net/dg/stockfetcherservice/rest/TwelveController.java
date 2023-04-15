package net.dg.stockfetcherservice.rest;

import lombok.AllArgsConstructor;
import net.dg.stockfetcherservice.exceptions.TwelveApiException;
import net.dg.stockfetcherservice.model.TwelveResponseBody;
import net.dg.stockfetcherservice.service.TwelveValidationService;
import net.dg.stockfetcherservice.service.impl.TwelveStockServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TwelveController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TwelveController.class);

  private final TwelveStockServiceImpl twelveStockService;
  private final TwelveValidationService twelveValidationService;

  @GetMapping
  public ResponseEntity<TwelveResponseBody> getStockInfo(@RequestParam String symbol) {

    try {
      TwelveResponseBody twelveResponseBody = twelveStockService.getStockInfo(symbol);
      twelveValidationService.validateApiResponse(twelveResponseBody);
      return new ResponseEntity<>(twelveResponseBody, HttpStatus.OK);
    } catch (TwelveApiException ex) {
      throw ex;
    }
  }
}
