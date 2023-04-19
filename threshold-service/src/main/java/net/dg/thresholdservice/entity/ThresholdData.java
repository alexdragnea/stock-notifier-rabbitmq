package net.dg.thresholdservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThresholdData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  @ManyToOne
  @JoinColumn(name = "stock_data_id")
  private StockData stockData;

  private BigDecimal threshold;
}
