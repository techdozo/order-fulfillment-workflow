package dev.techdozo.order.persistence.repository;

import dev.techdozo.common.error.ResourceNotFoundException;
import dev.techdozo.order.application.domain.model.Order;
import dev.techdozo.order.application.domain.repository.OrderRepository;
import dev.techdozo.order.persistence.mapper.OrderPersistableMapper;
import dev.techdozo.order.persistence.repository.jpa.OrderJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderJpaRepository orderJpaRepository;

  public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
    this.orderJpaRepository = orderJpaRepository;
  }

  @Override
  @Transactional
  public Order save(Order order) {
    log.info("Saving Order");
    var orderPersistable = OrderPersistableMapper.MAPPER.map(order);
    orderPersistable = orderJpaRepository.save(orderPersistable);
    log.info("Order saved, order {}", orderPersistable);
    return OrderPersistableMapper.MAPPER.map(orderPersistable);
  }

  @Override
  @Transactional
  public Order get(Long orderId) {
    log.info("Fetching order for id {}", orderId);
    var orderPersistable =
        orderJpaRepository
            .findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    log.info("Fetched order, {}", orderPersistable);
    return OrderPersistableMapper.MAPPER.map(orderPersistable);
  }
}
