package net.dg.thresholdservice.config;

import feign.Retryer;
import net.dg.thresholdservice.client.retryer.CustomRetryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableFeignClients(basePackages = "net.dg.thresholdservice.client")
@EnableScheduling
public class ThresholdServiceConfig {

  @Bean
  public Retryer retryer() {
    return new CustomRetryer();
  }
}
