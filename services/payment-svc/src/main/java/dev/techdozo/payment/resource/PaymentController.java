package dev.techdozo.payment.resource;

import dev.techdozo.payment.application.domain.model.Payment;
import dev.techdozo.payment.application.domain.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
  @Autowired private PaymentRepository paymentRepository;

  @GetMapping("/payments")
  public ResponseEntity<List<Payment>> listPayments() {
    log.info("Getting all payments..");
    // For simplicity, keep API model same as domain model
    List<Payment> payments = paymentRepository.getAll();
    return new ResponseEntity<>(payments, HttpStatus.OK);
  }
}
