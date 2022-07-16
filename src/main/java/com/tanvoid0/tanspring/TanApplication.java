package com.tanvoid0.tanspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@SpringBootTest(classes = {TanApplication.class})
@RestController
public class TanApplication {

  @GetMapping("")
  public String index() {
    return "Tanspring running Perfectly fine.";
  }

  @GetMapping("/live/{value}")
  public String live(
      @PathVariable("value") String value
  ) {
    return "Live valuesfs: " + value;
  }

  public static void main(String[] args) {
    SpringApplication.run(TanApplication.class, args);
  }


}