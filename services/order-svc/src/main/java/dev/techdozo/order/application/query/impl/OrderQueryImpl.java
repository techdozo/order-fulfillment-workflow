package dev.techdozo.order.application.query.impl;

import dev.techdozo.order.application.domain.model.Order;
import dev.techdozo.order.application.domain.repository.OrderRepository;
import dev.techdozo.order.application.query.OrderQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderQueryImpl implements OrderQuery {
  private final OrderRepository orderRepository;

  @Override
  public Order getOrder(Long orderId) {
    log.info("Fetching Order for id {}", orderId);
    return orderRepository.get(orderId);
  }
}
