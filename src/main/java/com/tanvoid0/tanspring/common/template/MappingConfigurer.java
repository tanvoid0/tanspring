package com.tanvoid0.tanspring.common.template;

import org.modelmapper.ModelMapper;

public interface MappingConfigurer {
    void configure(ModelMapper mapper);
}
