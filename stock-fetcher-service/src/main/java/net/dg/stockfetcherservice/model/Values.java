package net.dg.stockfetcherservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Values {

  private String datetime;
  private String open;
  private String high;
  private String low;
  private String close;
  private String volume;
}
