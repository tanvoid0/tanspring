package com.tanvoid0.tanspring.core.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;

public abstract class AbstractMappingConfigurer implements MappingConfigurer {
  @Autowired
  @Lazy
  private ModelMapper mapper;

  @PostConstruct
  void configureMapper() {
    configure(mapper);
  }
}
