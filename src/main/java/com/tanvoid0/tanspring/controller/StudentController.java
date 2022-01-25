package com.tanvoid0.tanspring.controller;

import lombok.AllArgsConstructor;

import com.tanvoid0.tanspring.models.Student;
import com.tanvoid0.tanspring.service.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
