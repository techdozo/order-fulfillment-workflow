package dev.techdozo.payment.infrastructure.temporal.orchestrator.worker;

import dev.techdozo.common.TaskQueue;
import dev.techdozo.common.activities.DebitPaymentActivity;
import dev.techdozo.payment.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
public class PaymentWorker {

  private final DebitPaymentActivity debitPaymentActivity;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering Payment worker..");

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();

    var workerOptions = WorkerOptions.newBuilder().build();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker =
        workerFactory.newWorker(TaskQueue.PAYMENT_ACTIVITY_TASK_QUEUE.name(), workerOptions);

    worker.registerActivitiesImplementations(debitPaymentActivity);

    workerFactory.start();

    log.info("Registered Payment worker..");
  }
}
