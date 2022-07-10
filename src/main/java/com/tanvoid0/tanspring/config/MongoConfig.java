package com.tanvoid0.tanspring.config;


import com.tanvoid0.tanspring.security.role.ERole;
import com.tanvoid0.tanspring.security.role.Role;
import com.tanvoid0.tanspring.security.role.RoleRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Configuration
public class MongoConfig {
  @Value("${spring.data.mongodb.uri}")
  private String CONNECTION_STRING;

  @Bean
  CommandLineRunner runner(
      RoleRepository repository,
      MongoTemplate template
  ) {
    return args -> {
      checkRole(repository, template, ERole.ROLE_USER);
      checkRole(repository, template, ERole.ROLE_MODERATOR);
      checkRole(repository, template, ERole.ROLE_ADMIN);
    };
  }

  void checkRole(RoleRepository repository, MongoTemplate template, ERole role) {
    Query query = new Query();
    query.addCriteria(Criteria.where("name").is(role));
    List<Role> userRole = template.find(query, Role.class);

    if (userRole.size() > 1) {
      throw new IllegalStateException(
          "Found many roles with same name"
      );
    } else if (userRole.size() == 1) {
      System.out.println("Role " + role + " already exists");
    }

    if (userRole.isEmpty()) {
      Role r = Role.builder().name(role).build();
      System.out.println("Inserting new role " + role);
      repository.save(r);
    }
  }

}
