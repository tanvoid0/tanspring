package com.tanvoid0.tanspring;

import java.util.List;

import com.tanvoid0.tanspring.models.user.models.Role;
import com.tanvoid0.tanspring.enums.ERole;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
@SpringBootTest(classes = {TanApplication.class})
public class TanApplication {

  public static void main(String[] args) {
    SpringApplication.run(TanApplication.class, args);
  }
//
//  @Bean
//  CommandLineRunner runner(
//      RoleRepository repository,
//      MongoTemplate template
//  ) {
//    return args -> {
//      checkRole(repository, template, ERole.ROLE_USER);
//      checkRole(repository, template, ERole.ROLE_MODERATOR);
//      checkRole(repository, template, ERole.ROLE_ADMIN);
//    };
//  }
//
//  void checkRole(RoleRepository repository, MongoTemplate template, ERole role) {
//    Query query = new Query();
//    query.addCriteria(Criteria.where("name").is(role));
//    List<Role> userRole = template.find(query, Role.class);
//
//    if (userRole.size() > 1) {
//      throw new IllegalStateException(
//          "Found many roles with same name"
//      );
//    } else if (userRole.size() == 1) {
//      System.out.println("Role " + role + " already exists");
//    }
//
//    if (userRole.isEmpty()) {
//      Role r = new Role(
//          role
//      );
//      System.out.println("Inserting new role " + role);
//      repository.save(r);
//    }
//  }


}