package net.dg.stockfetcherservice.client.twelve;

import feign.Headers;
import net.dg.stockfetcherservice.config.StockFetcherServiceConfig;
import net.dg.stockfetcherservice.model.TwelveResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "TwelveStockClient",
    url = "${twelve.base.url}",
    configuration = StockFetcherServiceConfig.class,
    decode404 = true)
public interface TwelveStockClient {

  @GetMapping()
  @Headers("X-RapidAPI-Key: 6fe169f0e2msh8ee3234f44542bbp1cd392jsn55822c38d478")
  TwelveResponseBody getStockInfo(
      @RequestParam("symbol") String symbol,
      @RequestParam("outputsize") String outputSize,
      @RequestHeader("X-RapidAPI-Key") String apikey);
}
