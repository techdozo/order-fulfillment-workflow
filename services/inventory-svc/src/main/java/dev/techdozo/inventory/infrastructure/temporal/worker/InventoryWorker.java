package dev.techdozo.inventory.infrastructure.temporal.worker;

import dev.techdozo.common.TaskQueue;
import dev.techdozo.common.activities.ReserveInventoryActivity;
import dev.techdozo.inventory.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
public class InventoryWorker {

  private final ReserveInventoryActivity reserveInventoryActivity;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering worker..");

    var workerOptions = WorkerOptions.newBuilder().build();

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker =
        workerFactory.newWorker(TaskQueue.INVENTORY_ACTIVITY_TASK_QUEUE.name(), workerOptions);

    worker.registerActivitiesImplementations(reserveInventoryActivity);

    workerFactory.start();

    log.info("Inventory worker started..");
  }
}
