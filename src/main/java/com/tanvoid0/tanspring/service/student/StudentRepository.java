package com.tanvoid0.tanspring.service.student;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository
    extends MongoRepository<Student, String> {
  Optional<Student> findStudentByEmail(String email);
}