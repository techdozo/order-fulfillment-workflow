package dev.techdozo.shipment.config;

import dev.techdozo.common.activities.ShipGoodsActivity;
import dev.techdozo.shipment.application.domain.repository.ShipmentRepository;
import dev.techdozo.shipment.application.domain.service.ShipmentService;
import dev.techdozo.shipment.application.domain.service.ShipmentServiceImpl;
import dev.techdozo.shipment.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import dev.techdozo.shipment.infrastructure.temporal.worker.ShipmentWorker;
import dev.techdozo.shipment.infrastructure.temporal.workflow.activity.impl.ShipGoodsActivityImpl;
import dev.techdozo.shipment.persistence.repository.ShipmentRepositoryImpl;
import dev.techdozo.shipment.persistence.repository.jpa.ShipmentJpaRepository;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class ShipmentServiceAppConfig {

  @Bean
  public ShipmentWorker shipmentWorker(ShipmentJpaRepository shipmentJpaRepository) {
    return new ShipmentWorker(
        shipGoodsActivity(shipmentJpaRepository), workflowOrchestratorClient());
  }

  @Bean
  public ShipGoodsActivity shipGoodsActivity(ShipmentJpaRepository shipmentJpaRepository) {
    return new ShipGoodsActivityImpl(shipmentService(), shipmentRepository(shipmentJpaRepository));
  }

  @Bean
  public ShipmentService shipmentService() {
    return new ShipmentServiceImpl();
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
  public ShipmentRepository shipmentRepository(ShipmentJpaRepository shipmentJpaRepository) {
    return new ShipmentRepositoryImpl(shipmentJpaRepository);
  }
}
