package dev.techdozo.inventory.persistence.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Entity
@Table(name = "inventory")
public class InventoryPersistable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long orderId;
  private String productId;
  private Double quantity;
}
