package dev.techdozo.order.common.config;

import dev.techdozo.common.activities.CompleteOrderActivity;
import dev.techdozo.order.application.command.OrderCommand;
import dev.techdozo.order.application.command.impl.OrderCommandImpl;
import dev.techdozo.order.application.domain.repository.OrderRepository;
import dev.techdozo.order.application.factory.OrderFactory;
import dev.techdozo.order.application.factory.impl.OrderFactoryImpl;
import dev.techdozo.order.application.orchestration.WorkflowOrchestrator;
import dev.techdozo.order.application.query.OrderQuery;
import dev.techdozo.order.application.query.impl.OrderQueryImpl;
import dev.techdozo.order.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import dev.techdozo.order.infrastructure.temporal.orchestrator.WorkflowOrchestratorImpl;
import dev.techdozo.order.infrastructure.temporal.worker.OrderWorker;
import dev.techdozo.order.infrastructure.temporal.workflow.activity.impl.CompleteOrderActivityImpl;
import dev.techdozo.order.persistence.repository.OrderRepositoryImpl;
import dev.techdozo.order.persistence.repository.jpa.OrderJpaRepository;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class OrderServiceAppConfig {

  @Bean
  public OrderFactory orderFactory(OrderJpaRepository orderJpaRepository) {
    return new OrderFactoryImpl(orderCommand(orderJpaRepository), orderQuery(orderJpaRepository));
  }

  private OrderQuery orderQuery(OrderJpaRepository orderJpaRepository) {
    return new OrderQueryImpl(orderRepository(orderJpaRepository));
  }

  @Bean
  public OrderCommand orderCommand(OrderJpaRepository orderJpaRepository) {
    return new OrderCommandImpl(orderRepository(orderJpaRepository), workflowOrchestrator());
  }

  @Bean
  public OrderWorker orderWorker(OrderJpaRepository orderJpaRepository) {
    return new OrderWorker(
        createPendingOrderActivity(orderJpaRepository), workflowOrchestratorClient());
  }

  @Bean
  public CompleteOrderActivity createPendingOrderActivity(OrderJpaRepository orderJpaRepository) {
    return new CompleteOrderActivityImpl(orderRepository(orderJpaRepository));
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
  public OrderRepository orderRepository(OrderJpaRepository orderJpaRepository) {
    return new OrderRepositoryImpl(orderJpaRepository);
  }

  @Bean
  public WorkflowOrchestrator workflowOrchestrator() {
    return new WorkflowOrchestratorImpl(workflowOrchestratorClient(), applicationProperties());
  }
}
