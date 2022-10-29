package dev.techdozo.shipment.resource;

import dev.techdozo.shipment.application.domain.model.Shipment;
import dev.techdozo.shipment.application.domain.repository.ShipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ShipmentController {
  @Autowired private ShipmentRepository shipmentRepository;

  @GetMapping("/shipments")
  public ResponseEntity<List<Shipment>> listShipment() {
    log.info("Getting all shipments..");
    List<Shipment> inventories = shipmentRepository.getAll();
    return new ResponseEntity<>(inventories, HttpStatus.OK);
  }
}
