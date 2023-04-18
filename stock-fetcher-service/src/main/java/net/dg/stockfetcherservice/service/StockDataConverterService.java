package net.dg.stockfetcherservice.service;

import java.time.Instant;
import net.dg.stockfetcherservice.model.Meta;
import net.dg.stockfetcherservice.model.TwelveResponseBody;
import net.dg.stockfetcherservice.model.Values;
import net.dg.stockfetcherservice.model.entity.StockData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StockDataConverterService {

  private static final Logger LOGGER = LoggerFactory.getLogger(StockDataConverterService.class);

  public StockData convertTwelveResponseToStockData(TwelveResponseBody twelveResponseBody) {

    LOGGER.info(
        "Converting TwelveResponseBody object: {} to StockData object.", twelveResponseBody);

    Meta meta = twelveResponseBody.getMeta();
    Values value = twelveResponseBody.getValues().get(0);
    return StockData.builder()
        .symbol(meta.getSymbol())
        .dateTime(value.getDatetime())
        .price(value.getClose())
        .currency(meta.getCurrency())
        .updatedAt(Instant.now())
        .build();
  }
}
