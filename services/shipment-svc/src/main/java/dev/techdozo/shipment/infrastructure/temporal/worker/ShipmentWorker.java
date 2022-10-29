package dev.techdozo.shipment.infrastructure.temporal.worker;

import dev.techdozo.common.TaskQueue;
import dev.techdozo.common.activities.ShipGoodsActivity;
import dev.techdozo.shipment.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
public class ShipmentWorker {

  private final ShipGoodsActivity shipGoodsActivity;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering worker..");

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker = workerFactory.newWorker(TaskQueue.SHIPPING_ACTIVITY_TASK_QUEUE.name());

    worker.registerActivitiesImplementations(shipGoodsActivity);

    workerFactory.start();

    log.info("Shipment worker started..");
  }
}
