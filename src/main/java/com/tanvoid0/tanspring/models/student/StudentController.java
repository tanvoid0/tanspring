package com.tanvoid0.tanspring.models.student;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GetMapping
  public List<Student> fetchAllStudents() {
    return studentService.getAllStudents();
  }
}