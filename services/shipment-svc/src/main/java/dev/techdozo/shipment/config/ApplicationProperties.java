package dev.techdozo.shipment.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationProperties {
  private String target;
  private String workflowId;
}
