package net.dg.notificationservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notification implements Serializable {

  private String email;
  private String symbol;
  private String currency;
  private BigDecimal threshold;
  private BigDecimal price;
  private Instant updatedAt;
}
