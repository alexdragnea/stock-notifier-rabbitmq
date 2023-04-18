package net.dg.thresholdservice.client.stockfetcher;

import net.dg.thresholdservice.entity.StockData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "StockFetcherClient", url = "${stock-fetcher.base.url}", decode404 = true)
public interface StockFetcherClient {

  @GetMapping
  StockData getStockData(@RequestParam("symbol") String symbol);
}
