package dev.techdozo.shipment.application.domain.service;

import lombok.SneakyThrows;

import java.util.UUID;

/** Mock implementation to represent call to external service to initiate shipping */
public class ShipmentServiceImpl implements ShipmentService {
  @SneakyThrows
  @Override
  public String shipGoods(Double quantity) {
    UUID uuid = UUID.randomUUID();
    Thread.sleep(2000);
    return uuid.toString();
  }
}
