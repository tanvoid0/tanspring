package com.tanvoid0.tanspring.models.todo;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@Api(value = "TODO Controller")
@RestController
@RequestMapping("/api/v1/todo")
@Slf4j
public class TodoController {
  @Autowired
  private TodoService service;

  @GetMapping
  @PreAuthorize(("hasRole('USER')"))
  public List<TodoVO> getAll() {
    log.info("GET All TODO");
    return service.getAll();
  }

  @GetMapping("/{id}")
  public TodoVO get(@PathVariable("id") final long id) {
    log.info("GET Todo by id  {}", id);
    return service.get(id);
  }

  @PostMapping
  public TodoVO create(@Valid @RequestBody final NewTodoVO newVO) {
    log.info("CREATE Todo {}", newVO);
    return service.create(newVO);
  }
}
