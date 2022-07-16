package com.tanvoid0.tanspring.models.user.service;

import com.tanvoid0.tanspring.core.mapping.AbstractMappingConfigurer;
import com.tanvoid0.tanspring.models.user.models.NewUserVO;
import com.tanvoid0.tanspring.models.user.models.UpdateUserVO;
import com.tanvoid0.tanspring.models.user.models.User;
import com.tanvoid0.tanspring.models.user.models.UserVO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userMappingConfigurer")
public class UserMappingConfigurer extends AbstractMappingConfigurer {
  @Autowired
  private UserRepository repository;

  @Override
  public void configure(final ModelMapper mapper) {
    configureEntityToVOMappings(mapper);
    configureVOToEntityMappings(mapper);
  }

  private void configureVOToEntityMappings(ModelMapper mapper) {
    mapper.typeMap(UserVO.class, User.class)
        .addMappings(x -> x.map(UserVO::getRoles, User::setRoles));
    mapper.typeMap(UpdateUserVO.class, User.class)
        .addMappings(x -> x.map(UpdateUserVO::getRoles, User::setRoles));
    mapper.typeMap(NewUserVO.class, User.class);
//        .addMappings(x -> x.map(NewUserVO::getRoles, User::setRoles));
  }

  private void configureEntityToVOMappings(ModelMapper mapper) {
    mapper.typeMap(User.class, UserVO.class)
        .addMappings(x -> x.map(User::getRoles, UserVO::setRoles));
    mapper.typeMap(User.class, UpdateUserVO.class)
        .addMappings(x -> x.map(User::getRoles, UpdateUserVO::setRoles));
    mapper.typeMap(User.class, NewUserVO.class);
//        .addMappings(x -> x.map(User::getRoles, NewUserVO::setRoles));
  }


}
