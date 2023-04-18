package net.dg.thresholdservice.config;

import feign.Retryer;
import net.dg.thresholdservice.client.retryer.CustomRetryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "net.dg.thresholdservice.client")
public class ThresholdServiceConfig {

  @Bean
  public Retryer retryer() {
    return new CustomRetryer();
  }

  //    @Bean
  //    public FeignErrorDecoder feignErrorDecoder() {
  //        return new FeignErrorDecoder();
  //    }
}
