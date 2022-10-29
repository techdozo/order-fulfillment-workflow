package dev.techdozo.order.persistence.repository.jpa;

import dev.techdozo.order.persistence.repository.model.OrderPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderPersistable, Long> {}
