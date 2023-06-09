package net.dg.stockfetcherservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TwelveResponseBody {

  private Meta meta;
  private List<Values> values;
  private String status;
  private String code;
  private String message;
}
