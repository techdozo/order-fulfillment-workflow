package dev.techdozo.order.application.command.impl;

import dev.techdozo.order.application.command.OrderCommand;
import dev.techdozo.order.application.domain.model.Order;
import dev.techdozo.order.application.domain.model.OrderStatus;
import dev.techdozo.order.application.domain.repository.OrderRepository;
import dev.techdozo.order.application.orchestration.WorkflowOrchestrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OrderCommandImpl implements OrderCommand {

  private final OrderRepository orderRepository;
  private final WorkflowOrchestrator workflowOrchestrator;

  @Override
  public Order createOrder(Order order) {
    log.info("Creating order..");
    order.setOrderStatus(OrderStatus.PENDING);
    var persistedOrder = orderRepository.save(order);
    workflowOrchestrator.createOrder(persistedOrder);
    return persistedOrder;
  }
}
