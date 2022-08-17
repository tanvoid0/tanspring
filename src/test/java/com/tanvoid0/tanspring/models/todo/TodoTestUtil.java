package com.tanvoid0.tanspring.models.todo;

import java.util.List;

public class TodoTestUtil {
  public static List<TodoVO> getList() {
    return List.of(
        TodoVO.builder().id(1).title("TODO1").description("First Todo").build(),
        TodoVO.builder().id(2).title("TODO2").description("SecondTodo").build()
    );
  }
}
