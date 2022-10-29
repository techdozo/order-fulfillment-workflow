package dev.techdozo.payment.application.service.impl;

import dev.techdozo.payment.application.service.PaymentService;

import java.util.UUID;

public class PaymentServiceImpl implements PaymentService {
  @Override
  public String debit(Double amount) {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }
}
