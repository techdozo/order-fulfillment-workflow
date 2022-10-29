package dev.techdozo.order.resource.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class OrderRequest {

  @NotNull(message = "Product ID is required.")
  @NotBlank(message = "Product ID cannot be blank.")
  private Long productId;

  @NotNull(message = "Price is required.")
  private Double price;

  @NotNull(message = "Quantity is required.")
  private Double quantity;
}
