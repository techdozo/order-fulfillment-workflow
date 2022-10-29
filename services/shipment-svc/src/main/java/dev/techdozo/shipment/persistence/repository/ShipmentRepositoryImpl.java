package dev.techdozo.shipment.persistence.repository;

import dev.techdozo.shipment.application.domain.model.Shipment;
import dev.techdozo.shipment.application.domain.repository.ShipmentRepository;
import dev.techdozo.shipment.persistence.mapper.ShipmentPersistableMapper;
import dev.techdozo.shipment.persistence.repository.jpa.ShipmentJpaRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ShipmentRepositoryImpl implements ShipmentRepository {

  private final ShipmentJpaRepository shipmentJpaRepository;

  public ShipmentRepositoryImpl(ShipmentJpaRepository shipmentJpaRepository) {
    this.shipmentJpaRepository = shipmentJpaRepository;
  }

  @Override
  public Shipment save(Shipment shipment) {
    log.info("Saving Shipment");
    var shipmentPersistable = ShipmentPersistableMapper.MAPPER.map(shipment);
    shipmentPersistable = shipmentJpaRepository.save(shipmentPersistable);
    log.info("Shipment saved, id {}", shipmentPersistable.getId());
    return ShipmentPersistableMapper.MAPPER.map(shipmentPersistable);
  }

  @Override
  public List<Shipment> getAll() {
    log.info("Getting all shipments from DB..");
    var shipmentPersistables = shipmentJpaRepository.findAll();
    return shipmentPersistables.stream().map(ShipmentPersistableMapper.MAPPER::map).toList();
  }
}
