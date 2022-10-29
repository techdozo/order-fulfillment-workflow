package dev.techdozo.inventory.config;

import dev.techdozo.common.activities.ReserveInventoryActivity;
import dev.techdozo.inventory.application.domain.repository.InventoryRepository;
import dev.techdozo.inventory.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import dev.techdozo.inventory.infrastructure.temporal.worker.InventoryWorker;
import dev.techdozo.inventory.infrastructure.temporal.workflow.activity.impl.ReserveInventoryActivityImpl;
import dev.techdozo.inventory.persistence.repository.InventoryRepositoryImpl;
import dev.techdozo.inventory.persistence.repository.jpa.InventoryJpaRepository;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class InventoryServiceAppConfig {

  @Bean
  public InventoryWorker inventoryWorker(InventoryJpaRepository inventoryJpaRepository) {
    return new InventoryWorker(
        reserveInventoryActivity(inventoryJpaRepository), workflowOrchestratorClient());
  }

  @Bean
  public ReserveInventoryActivity reserveInventoryActivity(
      InventoryJpaRepository inventoryJpaRepository) {
    return new ReserveInventoryActivityImpl(inventoryRepository(inventoryJpaRepository));
  }

  @Bean
  @ConfigurationProperties
  public ApplicationProperties applicationProperties() {
    return new ApplicationProperties();
  }

  @Bean
  public WorkflowOrchestratorClient workflowOrchestratorClient() {
    return new WorkflowOrchestratorClient(applicationProperties());
  }

  @Bean
  public InventoryRepository inventoryRepository(InventoryJpaRepository inventoryJpaRepository) {
    return new InventoryRepositoryImpl(inventoryJpaRepository);
  }
}
