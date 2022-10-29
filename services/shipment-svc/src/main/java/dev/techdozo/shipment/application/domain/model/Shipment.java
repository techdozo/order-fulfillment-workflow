package dev.techdozo.shipment.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Shipment Domain object */
@Setter
@Getter
@ToString
@Builder
public class Shipment {
  private Long shipmentId;
  private Long orderId;
  private Long productId;
  private String trackingId;
  private Double quantity;
}
