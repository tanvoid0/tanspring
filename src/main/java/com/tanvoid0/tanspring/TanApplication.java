package com.tanvoid0.tanspring;

import com.tanvoid0.tanspring.models.Address;
import com.tanvoid0.tanspring.models.ERole;
import com.tanvoid0.tanspring.models.Gender;
import com.tanvoid0.tanspring.models.Role;
import com.tanvoid0.tanspring.models.Student;
import com.tanvoid0.tanspring.repository.RoleRepository;
import com.tanvoid0.tanspring.repository.StudentRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TanApplication {

  public static void main(String[] args) {
    SpringApplication.run(TanApplication.class, args);
  }

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
      Role r = new Role(
          role
      );
      System.out.println("Inserting new role " + role);
      repository.save(r);
    }
  }

  // TODO: Need to add these data to the database before running authentiations
  /**
   * db.roles.insertMany([
   *    { name: "ROLE_USER" },
   *    { name: "ROLE_MODERATOR" },
   *    { name: "ROLE_ADMIN" },
   * ])
   */
  //
  //  @Bean CommandLineRunner runner(
  //      StudentRepository repository,
  //      MongoTemplate template) {
  //    return args -> {
  //      Address address = new Address(
  //          "England",
  //          "London",
  //          "NE9"
  //      );
  //      String email = "jahmed@gmail.com";
  //      Student student = new Student(
  //          "Jamila",
  //          "Ahmed",
  //          email,
  //          Gender.FEMALE,
  //          address,
  //          List.of("Computer Science", "Maths"),
  //          BigDecimal.TEN,
  //          LocalDateTime.now()
  //      );
  //
  ////      usingMongoTemplateAndQuery(repository, template, email, student);
  //
  //      repository.findStudentByEmail(email).ifPresentOrElse(
  //          s-> {
  //            System.out.println(s + " already exists");
  //          },
  //          () -> {
  //            System.out.println("Inserting student " + student);
  //            repository.save(student);
  //          }
  //      );
  //
  //    };
  //  }
  //
  //  private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate template, String email, Student student) {
  //    Query query = new Query();
  //    query.addCriteria(Criteria.where("email").is(email));
  //
  //    List<Student> students = template.find(query, Student.class);
  //
  //    if(students.size() > 1){
  //      throw new IllegalStateException(
  //          "found many students with email "+ email);
  //    }
  //
  //    if(students.isEmpty()){
  //      System.out.println("Inserting student " + student);
  //      repository.save(student);
  //    } else {
  //      System.out.println(student + " already exists");
  //    }
  //  }

}