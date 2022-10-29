package dev.techdozo.payment.persistence.repository.jpa;

import dev.techdozo.payment.persistence.repository.model.PaymentPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentPersistable, Long> {}
