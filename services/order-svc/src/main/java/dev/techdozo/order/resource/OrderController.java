package dev.techdozo.order.resource;

import dev.techdozo.order.application.factory.OrderFactory;
import dev.techdozo.order.resource.mapper.OrderRequestMapper;
import dev.techdozo.order.resource.model.OrderRequest;
import dev.techdozo.order.resource.model.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class OrderController {
  @Autowired private OrderFactory orderFactory;

  @PostMapping("/orders")
  public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {

    log.info("Creating order, product id {}", orderRequest.getProductId());

    var order = OrderRequestMapper.MAPPER.map(orderRequest);
    var orderCommand = orderFactory.getOrderCommand();

    var pendingOrder = orderCommand.createOrder(order);

    var orderResponse = new OrderResponse(pendingOrder.getOrderId(), pendingOrder.getOrderStatus());
    return new ResponseEntity<>(orderResponse, HttpStatus.ACCEPTED);
  }

  @GetMapping("/orders/{orderId}")
  public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {

    log.info("Getting order, id {}", orderId);

    var orderQuery = orderFactory.getOrderQuery();

    var order = orderQuery.getOrder(orderId);

    var orderResponse = new OrderResponse(order.getOrderId(), order.getOrderStatus());
    return new ResponseEntity<>(orderResponse, HttpStatus.OK);
  }
}
