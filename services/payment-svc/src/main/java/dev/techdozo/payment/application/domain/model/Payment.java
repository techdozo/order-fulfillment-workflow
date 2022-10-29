package dev.techdozo.payment.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Payment Domain object */
@Setter
@Getter
@ToString
@Builder
public class Payment {
  private Long paymentId;
  private Long orderId;
  private Long productId;
  private Double amount;
  private String externalId;
}
