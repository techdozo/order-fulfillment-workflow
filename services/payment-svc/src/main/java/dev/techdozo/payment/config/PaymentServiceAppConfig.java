package dev.techdozo.payment.config;

import dev.techdozo.common.activities.DebitPaymentActivity;
import dev.techdozo.payment.application.domain.repository.PaymentRepository;
import dev.techdozo.payment.application.service.PaymentService;
import dev.techdozo.payment.application.service.impl.PaymentServiceImpl;
import dev.techdozo.payment.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import dev.techdozo.payment.infrastructure.temporal.orchestrator.worker.PaymentWorker;
import dev.techdozo.payment.infrastructure.temporal.workflow.activity.impl.DebitPaymentActivityImpl;
import dev.techdozo.payment.persistence.repository.PaymentRepositoryImpl;
import dev.techdozo.payment.persistence.repository.jpa.PaymentJpaRepository;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class PaymentServiceAppConfig {

  @Bean
  public PaymentWorker paymentWorker(PaymentJpaRepository paymentJpaRepository) {
    return new PaymentWorker(
        debitPaymentActivity(paymentJpaRepository), workflowOrchestratorClient());
  }

  @Bean
  public DebitPaymentActivity debitPaymentActivity(PaymentJpaRepository paymentJpaRepository) {
    return new DebitPaymentActivityImpl(paymentService(), paymentRepository(paymentJpaRepository));
  }

  @Bean
  public PaymentService paymentService() {
    return new PaymentServiceImpl();
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
  public PaymentRepository paymentRepository(PaymentJpaRepository paymentJpaRepository) {
    return new PaymentRepositoryImpl(paymentJpaRepository);
  }
}
