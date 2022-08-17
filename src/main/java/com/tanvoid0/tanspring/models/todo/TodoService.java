package com.tanvoid0.tanspring.models.todo;

import java.util.List;

public interface TodoService {

  List<TodoVO> getAll();

  TodoVO get(long id);

  TodoVO create(NewTodoVO newVO);

  TodoVO update(UpdateTodoVO updateVO);

  boolean delete(long id);
}
