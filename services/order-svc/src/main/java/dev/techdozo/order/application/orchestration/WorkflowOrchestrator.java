package dev.techdozo.order.application.orchestration;

import dev.techdozo.order.application.domain.model.Order;

public interface WorkflowOrchestrator {
  void createOrder(Order order);
}
