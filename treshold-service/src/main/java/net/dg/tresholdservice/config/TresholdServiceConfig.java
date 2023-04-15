package net.dg.tresholdservice.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "net.dg.tresholdservice.client.stockfetcher")
public class TresholdServiceConfig {
    
}
