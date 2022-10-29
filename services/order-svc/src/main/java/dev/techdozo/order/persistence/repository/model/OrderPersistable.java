package dev.techdozo.order.persistence.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Entity
@Table(name = "product_order")
public class OrderPersistable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String productId;
  private String orderStatus;
  private Double price;
  private Double quantity;
}
