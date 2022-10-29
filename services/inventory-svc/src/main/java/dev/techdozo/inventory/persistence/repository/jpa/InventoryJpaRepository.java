package dev.techdozo.inventory.persistence.repository.jpa;

import dev.techdozo.inventory.persistence.repository.model.InventoryPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryJpaRepository extends JpaRepository<InventoryPersistable, Long> {}
