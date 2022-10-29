package dev.techdozo.order.infrastructure.temporal.workflow;

import dev.techdozo.common.model.OrderDTO;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderFulfillmentWorkflow {
  @WorkflowMethod
  void createOrder(OrderDTO orderDTO);
}
