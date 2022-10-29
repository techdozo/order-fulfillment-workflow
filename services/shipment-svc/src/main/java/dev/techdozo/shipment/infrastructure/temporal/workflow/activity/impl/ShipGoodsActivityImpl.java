package dev.techdozo.shipment.infrastructure.temporal.workflow.activity.impl;

import dev.techdozo.common.activities.ShipGoodsActivity;
import dev.techdozo.common.model.OrderDTO;
import dev.techdozo.shipment.application.domain.model.Shipment;
import dev.techdozo.shipment.application.domain.repository.ShipmentRepository;
import dev.techdozo.shipment.application.domain.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ShipGoodsActivityImpl implements ShipGoodsActivity {

  private final ShipmentService shipmentService;
  private final ShipmentRepository shipmentRepository;

  @Override
  public void shipGoods(OrderDTO orderDTO) {

    log.info("Dispatching shipment,  order id {}", orderDTO.getOrderId());
    var trackingId = shipmentService.shipGoods(orderDTO.getQuantity());

    var shipment =
        Shipment.builder()
            .orderId(orderDTO.getOrderId())
            .productId(orderDTO.getProductId())
            .quantity(orderDTO.getQuantity())
            .trackingId(trackingId)
            .build();
    shipmentRepository.save(shipment);

    log.info("Created shipment for order id {}", orderDTO.getOrderId());
  }
}
