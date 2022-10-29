package dev.techdozo.inventory.resource;

import dev.techdozo.inventory.application.domain.model.Inventory;
import dev.techdozo.inventory.application.domain.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class InventoryController {
  @Autowired private InventoryRepository inventoryRepository;

  @GetMapping("/inventories")
  public ResponseEntity<List<Inventory>> listInventory() {
    log.info("Getting all inventory..");
    List<Inventory> inventories = inventoryRepository.getAll();
    return new ResponseEntity<>(inventories, HttpStatus.OK);
  }
}
