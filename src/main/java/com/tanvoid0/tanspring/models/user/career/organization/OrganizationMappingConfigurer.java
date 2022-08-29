package com.tanvoid0.tanspring.models.user.career.organization;

import com.tanvoid0.tanspring.common.vo.AbstractMappingConfigurer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component("organizationMappingConfigurer")
public class OrganizationMappingConfigurer extends AbstractMappingConfigurer {

    @Override
    public void configure(final ModelMapper mapper) {
        configureEntityToVOMappings(mapper);
        configureVOToEntityMappings(mapper);
    }

    private void configureEntityToVOMappings(final ModelMapper mapper) {
    }

    private void configureVOToEntityMappings(final ModelMapper mapper) {
    }

}
