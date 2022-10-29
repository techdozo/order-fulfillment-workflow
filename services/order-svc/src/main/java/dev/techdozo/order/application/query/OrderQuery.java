package dev.techdozo.order.application.query;

import dev.techdozo.order.application.domain.model.Order;

public interface OrderQuery {
  Order getOrder(Long orderId);
}
