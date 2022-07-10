package com.tanvoid0.tanspring.core.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfiguration {
  @Bean
  public ModelMapper modelMapper() {
    final ModelMapper mapper = new ModelMapper();

    mapper.getConfiguration()
        .setSkipNullEnabled(false)
        .setMatchingStrategy(MatchingStrategies.STRICT);
    return mapper;
  }
}
