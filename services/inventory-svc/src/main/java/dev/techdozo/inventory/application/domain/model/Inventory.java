package dev.techdozo.inventory.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Inventory Domain object */
@Setter
@Getter
@ToString
@Builder
public class Inventory {
  private Long inventoryId;
  private Long orderId;
  private Long productId;
  private Double quantity;
}
