package dev.techdozo.inventory.persistence.repository;

import dev.techdozo.inventory.application.domain.model.Inventory;
import dev.techdozo.inventory.application.domain.repository.InventoryRepository;
import dev.techdozo.inventory.persistence.mapper.InventoryPersistableMapper;
import dev.techdozo.inventory.persistence.repository.jpa.InventoryJpaRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class InventoryRepositoryImpl implements InventoryRepository {

  private final InventoryJpaRepository inventoryJpaRepository;

  public InventoryRepositoryImpl(InventoryJpaRepository inventoryJpaRepository) {
    this.inventoryJpaRepository = inventoryJpaRepository;
  }

  @Override
  public Inventory save(Inventory inventory) {
    log.info("Saving Inventory");
    var inventoryPersistable = InventoryPersistableMapper.MAPPER.map(inventory);
    inventoryPersistable = inventoryJpaRepository.save(inventoryPersistable);
    log.info("Inventory saved, id {}", inventoryPersistable.getId());
    return InventoryPersistableMapper.MAPPER.map(inventoryPersistable);
  }

  @Override
  public List<Inventory> getAll() {
    log.info("Getting all inventory from DB..");
    var inventoryPersistables = inventoryJpaRepository.findAll();
    return inventoryPersistables.stream().map(InventoryPersistableMapper.MAPPER::map).toList();
  }
}
