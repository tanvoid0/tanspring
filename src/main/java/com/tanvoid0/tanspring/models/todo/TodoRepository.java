package com.tanvoid0.tanspring.models.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  List<Todo> findAllByUserId(long userId);
}
