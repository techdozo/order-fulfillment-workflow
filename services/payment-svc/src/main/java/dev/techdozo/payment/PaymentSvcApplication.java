package dev.techdozo.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PaymentSvcApplication {

  public static void main(String[] args) {
    SpringApplication.run(PaymentSvcApplication.class, args);
  }
}
