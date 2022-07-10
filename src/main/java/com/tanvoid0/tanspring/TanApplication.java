package com.tanvoid0.tanspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootApplication
@SpringBootTest(classes = {TanApplication.class})
public class TanApplication {

  public static void main(String[] args) {
    SpringApplication.run(TanApplication.class, args);
  }


}