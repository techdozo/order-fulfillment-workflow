package dev.techdozo.order.resource.mapper;

import dev.techdozo.order.application.domain.model.Order;
import dev.techdozo.order.resource.model.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OrderRequestMapper {

  public static final OrderRequestMapper MAPPER = Mappers.getMapper(OrderRequestMapper.class);

  public abstract Order map(OrderRequest request);
}
