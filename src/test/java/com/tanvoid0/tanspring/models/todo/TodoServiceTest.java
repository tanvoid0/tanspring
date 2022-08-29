package com.tanvoid0.tanspring.models.todo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import lombok.extern.slf4j.Slf4j;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;


@Slf4j
class TodoServiceTest {

    private TodoService service;

    @Mock
    private TodoRepository repository;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper mapper;


    private List<TodoVO> todoVOList;
    private List<Todo> todoList;
    private NewTodoVO newTodoVO;
    private UpdateTodoVO updateTodoVO;
    private Todo todo;
    private Todo savedTodo;
    private TodoVO todoVO;
    private Long userId;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

        service = new TodoServiceImpl(repository, userService, mapper);

        userId = -1L;
        todoVO = TodoVO.builder().userId(userId).id(1L).title("Task 1").description("Task Description").build();
        todoVOList = List.of(
                todoVO,
                todoVO.toBuilder().id(2L).title("Task 2").build()
        );

        todo = Todo.builder().userId(userId).title("Task 1").description("Task Description").userId(userId).build();
        todoList = List.of(
                todo.toBuilder().id(1L).build(),
                todo.toBuilder().id(2L).title("Task 2").build()
        );

        newTodoVO = NewTodoVO.builder().title("Task 1").description("Description").build();
        updateTodoVO = UpdateTodoVO.builder().id(1L).title("Task 1").description("Task Description").build();

        savedTodo = todo.toBuilder().id(1L).build();

        when(userService.getAuthUserId()).thenReturn(userId);

    }

    @Test
    void initTest() {
        assertThat(mapper).isNotNull();
        assertThat(repository).isNotNull();
        assertThat(userService).isNotNull();
    }

    @Test
    void getAll() {
        // given

        // when
        when(repository.findAllByUserId(-1L)).thenReturn(todoList);
        for (int i = 0; i < todoList.size(); i++) {
            when(mapper.map(todoList.get(i), TodoVO.class)).thenReturn(todoVOList.get(i));
        }

        final List<TodoVO> result = service.getAll();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void get() {
        // given

        // when
        when(repository.findById(-1L)).thenReturn(Optional.of(todoList.get(0)));
        when(mapper.map(todoList.get(0), TodoVO.class)).thenReturn(todoVOList.get(0));

        // then
        final TodoVO result = service.get(-1L);
        assertThat(result).isEqualTo(todoVOList.get(0));
    }

    @Test
    void get_throwsNotFoundException() {
        ResourceNotFoundException ex = Assertions.assertThrows(ResourceNotFoundException.class, () -> service.get(-99L));

        assertThat(ex.getMessage()).isEqualTo("todo not found with id='-99'");
        assertThat(ex.getResourceName()).isEqualTo("todo");
        assertThat(ex.getFieldName()).isEqualTo("id");
        assertThat(ex.getFieldValue()).isEqualTo("-99");

    }

    @Test
    void create() {
        // given

        // when
        when(mapper.map(newTodoVO, Todo.class)).thenReturn(todo);
        when(repository.save(todo)).thenReturn(savedTodo);
        when(mapper.map(savedTodo, TodoVO.class)).thenReturn(todoVO);

        // then
        final TodoVO result = service.create(newTodoVO);
        assertThat(result).isEqualTo(todoVO);
    }

    @Test
    void update() {
        // given

        // when
        when(repository.findById(updateTodoVO.getId())).thenReturn(Optional.of(todo));
        when(mapper.map(todo, TodoVO.class)).thenReturn(todoVO);
        when(mapper.map(updateTodoVO, Todo.class)).thenReturn(todo);
        when(repository.save(todo)).thenReturn(savedTodo);
        when(mapper.map(savedTodo, TodoVO.class)).thenReturn(todoVO);

        // then
        final TodoVO result = service.update(updateTodoVO);

        assertThat(result).isEqualTo(todoVO);
    }

    @Test
    void delete() {
        // when
        when(repository.findById(1L)).thenReturn(Optional.of(todo));
        when(mapper.map(todo, TodoVO.class)).thenReturn(todoVO);

        // then
        final boolean result = service.delete(1);
        assertThat(result).isTrue();
    }
}