package dev.techdozo.order.application.factory;

import dev.techdozo.order.application.command.OrderCommand;
import dev.techdozo.order.application.query.OrderQuery;

public interface OrderFactory {

  OrderCommand getOrderCommand();

  OrderQuery getOrderQuery();
}
