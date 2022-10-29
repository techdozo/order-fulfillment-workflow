package dev.techdozo.shipment.infrastructure.temporal.orchestrator;

import dev.techdozo.shipment.config.ApplicationProperties;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkflowOrchestratorClient {
  private final ApplicationProperties applicationProperties;

  public WorkflowClient getWorkflowClient() {
    var workflowServiceStubsOptions =
        WorkflowServiceStubsOptions.newBuilder()
            .setTarget(applicationProperties.getTarget())
            .build();
    var workflowServiceStubs = WorkflowServiceStubs.newServiceStubs(workflowServiceStubsOptions);
    return WorkflowClient.newInstance(workflowServiceStubs);
  }
}
