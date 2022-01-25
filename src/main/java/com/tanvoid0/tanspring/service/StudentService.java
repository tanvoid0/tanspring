package com.tanvoid0.tanspring.service;

import lombok.AllArgsConstructor;

import com.tanvoid0.tanspring.models.Student;
import com.tanvoid0.tanspring.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }
}
