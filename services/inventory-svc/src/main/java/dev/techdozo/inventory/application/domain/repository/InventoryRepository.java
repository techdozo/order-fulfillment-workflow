package dev.techdozo.inventory.application.domain.repository;

import dev.techdozo.inventory.application.domain.model.Inventory;

import java.util.List;

/** Domain repository for the Inventory. */
public interface InventoryRepository {
  Inventory save(Inventory inventory);

  List<Inventory> getAll();
}
