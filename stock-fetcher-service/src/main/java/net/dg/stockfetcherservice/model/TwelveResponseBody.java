package net.dg.stockfetcherservice.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TwelveResponseBody {

  private List<Stock> data;
  private String status;
}
