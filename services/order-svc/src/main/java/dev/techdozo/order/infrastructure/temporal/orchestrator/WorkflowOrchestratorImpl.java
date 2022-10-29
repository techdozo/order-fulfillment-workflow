package dev.techdozo.order.infrastructure.temporal.orchestrator;

import dev.techdozo.common.TaskQueue;
import dev.techdozo.common.model.OrderDTO;
import dev.techdozo.order.application.domain.model.Order;
import dev.techdozo.order.application.orchestration.WorkflowOrchestrator;
import dev.techdozo.order.common.config.ApplicationProperties;
import dev.techdozo.order.infrastructure.temporal.workflow.OrderFulfillmentWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkflowOrchestratorImpl implements WorkflowOrchestrator {

  private final WorkflowOrchestratorClient workflowOrchestratorClient;
  private final ApplicationProperties applicationProperties;

  @Override
  public void createOrder(Order order) {

    var orderDTO = map(order);

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();
    var orderFulfillmentWorkflow =
        workflowClient.newWorkflowStub(
            OrderFulfillmentWorkflow.class,
            WorkflowOptions.newBuilder()
                .setWorkflowId(applicationProperties.getWorkflowId() + "-" + orderDTO.getOrderId())
                .setTaskQueue(TaskQueue.ORDER_FULFILLMENT_WORKFLOW_TASK_QUEUE.name())
                .build());
    // Execute Sync
    //    orderFulfillmentWorkflow.createOrder(orderDTO);
    // Async execution
    WorkflowClient.start(orderFulfillmentWorkflow::createOrder, orderDTO);
  }

  private OrderDTO map(Order order) {
    var orderDTO = new OrderDTO();
    orderDTO.setOrderId(order.getOrderId());
    orderDTO.setProductId(order.getProductId());
    orderDTO.setPrice(order.getPrice());
    orderDTO.setQuantity(order.getQuantity());
    return orderDTO;
  }
}
