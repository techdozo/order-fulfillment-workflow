package dev.techdozo.shipment.application.domain.repository;

import dev.techdozo.shipment.application.domain.model.Shipment;

import java.util.List;

/** Domain repository for the Shipment. */
public interface ShipmentRepository {
  Shipment save(Shipment shipment);

  List<Shipment> getAll();
}
