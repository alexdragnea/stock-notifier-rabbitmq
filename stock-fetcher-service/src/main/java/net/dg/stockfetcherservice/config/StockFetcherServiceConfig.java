package net.dg.stockfetcherservice.config;

import feign.Retryer;
import net.dg.stockfetcherservice.client.retryer.CustomRetryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableFeignClients(basePackages = "net.dg.stockfetcherservice.client")
public class StockFetcherServiceConfig {

  @Bean
  public Retryer retryer() {
    return new CustomRetryer();
  }
}
