package net.dg.tresholdservice.client.stockfetcher;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "StockFetcherClient", url = "${stock-fetcher.base.url}")
public interface StockFetcherClient {
}
