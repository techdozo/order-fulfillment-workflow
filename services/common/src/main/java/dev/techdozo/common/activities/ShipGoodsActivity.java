package dev.techdozo.common.activities;

import dev.techdozo.common.model.OrderDTO;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface ShipGoodsActivity {
  void shipGoods(OrderDTO orderDTO);
}
