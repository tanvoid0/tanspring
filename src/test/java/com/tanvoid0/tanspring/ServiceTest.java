package com.tanvoid0.tanspring;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.inject.Inject;


@ExtendWith(SpringExtension.class)
@Rollback
@SpringBootTest
@WithMockUser("admin")
public abstract class ServiceTest {

  @Inject
  private ObjectMapper objectMapper;

  @Value("${spring.application.name}")
  private String applicationName;
}
