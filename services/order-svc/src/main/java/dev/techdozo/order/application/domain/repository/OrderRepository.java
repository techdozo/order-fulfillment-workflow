package dev.techdozo.order.application.domain.repository;

import dev.techdozo.order.application.domain.model.Order;

/** Domain repository for the order. */
public interface OrderRepository {
  Order save(Order order);

  Order get(Long orderId);
}
