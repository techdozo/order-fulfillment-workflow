package dev.techdozo.order.infrastructure.temporal.workflow.impl;

import dev.techdozo.common.TaskQueue;
import dev.techdozo.common.activities.CompleteOrderActivity;
import dev.techdozo.common.activities.DebitPaymentActivity;
import dev.techdozo.common.activities.ReserveInventoryActivity;
import dev.techdozo.common.activities.ShipGoodsActivity;
import dev.techdozo.common.model.OrderDTO;
import dev.techdozo.order.infrastructure.temporal.workflow.OrderFulfillmentWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.activity.LocalActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;

public class OrderFulfillmentWorkflowImpl implements OrderFulfillmentWorkflow {

  private Logger logger = Workflow.getLogger(this.getClass().getName());

  private final ActivityOptions shippingActivityOptions =
      ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofMinutes(1))
          .setTaskQueue(TaskQueue.SHIPPING_ACTIVITY_TASK_QUEUE.name())
          .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
          .build();

  private final ActivityOptions paymentActivityOptions =
      ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofMinutes(1))
          .setTaskQueue(TaskQueue.PAYMENT_ACTIVITY_TASK_QUEUE.name())
          .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
          .build();
  private final ActivityOptions inventoryActivityOptions =
      ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofMinutes(1))
          .setTaskQueue(TaskQueue.INVENTORY_ACTIVITY_TASK_QUEUE.name())
          .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
          .build();
  private final LocalActivityOptions localActivityOptions =
      LocalActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofMinutes(1))
          .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(10).build())
          .build();

  private final CompleteOrderActivity orderActivity =
      Workflow.newLocalActivityStub(CompleteOrderActivity.class, localActivityOptions);

  private final DebitPaymentActivity paymentActivity =
      Workflow.newActivityStub(DebitPaymentActivity.class, paymentActivityOptions);

  private final ReserveInventoryActivity reserveInventoryActivity =
      Workflow.newActivityStub(ReserveInventoryActivity.class, inventoryActivityOptions);

  private final ShipGoodsActivity shipGoodsActivity =
      Workflow.newActivityStub(ShipGoodsActivity.class, shippingActivityOptions);

  @Override
  public void createOrder(OrderDTO orderDTO) {
    logger.info("Workflow ID {}", Workflow.getInfo().getWorkflowId());

    logger.info("Debiting payment..");
    paymentActivity.debitPayment(orderDTO);

    logger.info("Reserving inventory..");
    reserveInventoryActivity.reserveInventory(orderDTO);

    logger.info("Shipping goods..");
    shipGoodsActivity.shipGoods(orderDTO);

    logger.info("Completing order..");
    orderActivity.completeOrder(orderDTO);
  }
}
