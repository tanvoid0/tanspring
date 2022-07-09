package com.tanvoid0.tanspring.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

public class MongoConfig {
  @Value("${spring.data.mongodb.uri}")
  private String CONNECTION_STRING;
}
