package com.tanvoid0.tanspring.models.student;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.tanvoid0.tanspring.enums.Gender;
import com.tanvoid0.tanspring.models.user.models.Address;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Student {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  @Indexed(unique = true)
  private String email;
  private Gender gender;
  private Address address;
  private List<String> favouriteSubjects;
  private BigDecimal totalSpentInBooks;
  private LocalDateTime created;

  public Student(String firstName,
                 String lastName,
                 String email,
                 Gender gender,
                 Address address,
                 List<String> favouriteSubjects,
                 BigDecimal totalSpentInBooks,
                 LocalDateTime created) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
    this.address = address;
    this.favouriteSubjects = favouriteSubjects;
    this.totalSpentInBooks = totalSpentInBooks;
    this.created = created;
  }
}