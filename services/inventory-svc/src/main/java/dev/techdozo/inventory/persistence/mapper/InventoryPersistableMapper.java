package dev.techdozo.inventory.persistence.mapper;

import dev.techdozo.inventory.application.domain.model.Inventory;
import dev.techdozo.inventory.persistence.repository.model.InventoryPersistable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class InventoryPersistableMapper {

  public static final InventoryPersistableMapper MAPPER =
      Mappers.getMapper(InventoryPersistableMapper.class);

  @Mapping(source = "inventoryId", target = "id")
  public abstract InventoryPersistable map(Inventory inventory);

  @Mapping(source = "id", target = "inventoryId")
  public abstract Inventory map(InventoryPersistable inventoryPersistable);
}
