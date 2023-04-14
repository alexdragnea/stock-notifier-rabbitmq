package net.dg.stockfetcherservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {

  private String symbol;
  private String instrument_name;
  private String exchange;
  private String mic_code;
  private String exchange_timezone;
  private String instrument_type;
  private String country;
  private String currency;
}
