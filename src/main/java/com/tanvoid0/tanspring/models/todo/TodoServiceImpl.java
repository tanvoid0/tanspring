package com.tanvoid0.tanspring.models.todo;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.security.auth.User;
import com.tanvoid0.tanspring.security.auth.UserRepository;
import com.tanvoid0.tanspring.security.auth.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("todoService")
public class TodoServiceImpl implements TodoService {
  @Autowired
  private TodoRepository repository;

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper mapper;

  private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

  @Override
  public List<TodoVO> getAll() {
    List<Todo> todos = repository.findAllByUserId(userService.getId());
    return todos.stream().map(this::convertToVO).toList();
  }

  @Override
  public TodoVO get(long id) {
    Optional<Todo> todo = repository.findById(id);
    if (todo.isEmpty()) {
      throw new ResourceNotFoundException("todo", "id", id);
    }
    return convertToVO(todo.get());
  }

  @Override
  public TodoVO create(NewTodoVO newVO) {
    Todo todo = convertToEntity(newVO);
    todo.setUserId(userService.getId());
    Todo savedTodo = repository.save(todo);
    return convertToVO(savedTodo);
  }

  @Override
  public TodoVO update(UpdateTodoVO updateVO) {
    return null;
  }

  @Override
  public boolean delete(long id) {
    return false;
  }


  private Todo convertToEntity(final NewTodoVO newVO) {
    return mapper.map(newVO, Todo.class);
  }

  private Todo convertToEntity(final UpdateTodoVO updateVO) {
    return mapper.map(updateVO, Todo.class);
  }

  private TodoVO convertToVO(final Todo todo) {
    return mapper.map(todo, TodoVO.class);
  }
}
