package dev.techdozo.order.infrastructure.temporal.workflow.activity.impl;

import dev.techdozo.common.activities.CompleteOrderActivity;
import dev.techdozo.common.model.OrderDTO;
import dev.techdozo.order.application.domain.model.Order;
import dev.techdozo.order.application.domain.model.OrderStatus;
import dev.techdozo.order.application.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CompleteOrderActivityImpl implements CompleteOrderActivity {

  private final OrderRepository orderRepository;

  @Override
  public void completeOrder(OrderDTO orderDTO) {
    log.info("Marking order as completed, order id {}", orderDTO.getOrderId());
    var order = map(orderDTO);
    order.setOrderStatus(OrderStatus.COMPLETED);
    var completedOrder = orderRepository.save(order);
    log.info("Order completed, {}", completedOrder);
  }

  private Order map(OrderDTO orderDTO) {
    var order = new Order();
    order.setOrderId(orderDTO.getOrderId());
    order.setProductId(orderDTO.getProductId());
    order.setPrice(orderDTO.getPrice());
    order.setQuantity(orderDTO.getQuantity());
    return order;
  }
}
