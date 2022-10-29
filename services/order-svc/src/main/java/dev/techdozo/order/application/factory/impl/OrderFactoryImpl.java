package dev.techdozo.order.application.factory.impl;

import dev.techdozo.order.application.command.OrderCommand;
import dev.techdozo.order.application.factory.OrderFactory;
import dev.techdozo.order.application.query.OrderQuery;

public class OrderFactoryImpl implements OrderFactory {

  private final OrderCommand orderCommand;
  private final OrderQuery orderQuery;

  public OrderFactoryImpl(OrderCommand orderCommand, OrderQuery orderQuery) {
    this.orderCommand = orderCommand;
    this.orderQuery = orderQuery;
  }

  public OrderCommand getOrderCommand() {
    return orderCommand;
  }

  @Override
  public OrderQuery getOrderQuery() {
    return orderQuery;
  }
}
