package dev.techdozo.shipment.persistence.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Entity
@Table(name = "shipment")
public class ShipmentPersistable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long orderId;
  private String productId;
  private Double quantity;
  private String trackingId;
}
