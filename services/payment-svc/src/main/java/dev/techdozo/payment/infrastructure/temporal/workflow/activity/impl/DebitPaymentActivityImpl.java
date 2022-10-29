package dev.techdozo.payment.infrastructure.temporal.workflow.activity.impl;

import dev.techdozo.common.activities.DebitPaymentActivity;
import dev.techdozo.common.model.OrderDTO;
import dev.techdozo.payment.application.domain.model.Payment;
import dev.techdozo.payment.application.domain.repository.PaymentRepository;
import dev.techdozo.payment.application.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DebitPaymentActivityImpl implements DebitPaymentActivity {

  private final PaymentService paymentService;
  private final PaymentRepository paymentRepository;

  @Override
  public void debitPayment(OrderDTO orderDTO) {
    log.info("Processing payment for order {}", orderDTO.getOrderId());
    double amount = orderDTO.getQuantity() * orderDTO.getPrice();
    // Call external Payment service such as Stripe
    var externalPaymentId = paymentService.debit(amount);
    // Create domain object
    var payment =
        Payment.builder()
            .externalId(externalPaymentId)
            .orderId(orderDTO.getOrderId())
            .productId(orderDTO.getProductId())
            .amount(amount)
            .build();
    paymentRepository.save(payment);
  }
}
