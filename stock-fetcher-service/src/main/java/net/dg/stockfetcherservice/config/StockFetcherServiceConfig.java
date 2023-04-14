package net.dg.stockfetcherservice.config;

import net.dg.stockfetcherservice.client.errordecoder.FeignErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "net.dg.stockfetcherservice.client")
public class StockFetcherServiceConfig {

  @Bean
  public FeignErrorDecoder feignErrorDecoder() {
    return new FeignErrorDecoder();
  }
}
