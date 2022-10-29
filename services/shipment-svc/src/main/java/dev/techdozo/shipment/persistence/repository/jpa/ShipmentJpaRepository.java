package dev.techdozo.shipment.persistence.repository.jpa;

import dev.techdozo.shipment.persistence.repository.model.ShipmentPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentJpaRepository extends JpaRepository<ShipmentPersistable, Long> {}
