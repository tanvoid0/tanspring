package com.tanvoid0.tanspring.core.mapping;

import org.modelmapper.ModelMapper;

public interface MappingConfigurer {
  void configure(ModelMapper mapper);
}
