package dev.techdozo.inventory.infrastructure.temporal.workflow.activity.impl;

import dev.techdozo.common.activities.ReserveInventoryActivity;
import dev.techdozo.common.model.OrderDTO;
import dev.techdozo.inventory.application.domain.model.Inventory;
import dev.techdozo.inventory.application.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ReserveInventoryActivityImpl implements ReserveInventoryActivity {

  private final InventoryRepository inventoryRepository;

  @Override
  public void reserveInventory(OrderDTO orderDTO) {

    log.info("Processing inventory for order {}", orderDTO.getOrderId());

    var inventory =
        Inventory.builder()
            .orderId(orderDTO.getOrderId())
            .productId(orderDTO.getProductId())
            .quantity(orderDTO.getQuantity())
            .build();
    inventoryRepository.save(inventory);

    log.info("Finished processing inventory for order {}", orderDTO.getOrderId());
  }
}
