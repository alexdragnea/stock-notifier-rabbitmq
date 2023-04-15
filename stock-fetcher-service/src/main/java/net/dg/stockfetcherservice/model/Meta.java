package net.dg.stockfetcherservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meta {

  private String symbol;
  private String interval;
  private String currency;
  private String exchange_timezone;
  private String exchange;
  private String mic_code;
  private String type;
}
