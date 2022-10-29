package dev.techdozo.order.application.command;

import dev.techdozo.order.application.domain.model.Order;

public interface OrderCommand {
  Order createOrder(Order order);
}
