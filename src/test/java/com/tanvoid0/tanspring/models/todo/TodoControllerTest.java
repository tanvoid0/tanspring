package com.tanvoid0.tanspring.models.todo;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TodoController.class})
@ExtendWith(SpringExtension.class)
class TodoControllerTest {
  @Autowired
  private TodoController todoController;

  @MockBean
  private TodoService todoService;

  /**
   * Method under test: {@link TodoController#getAll()}
   */
  @Test
  void testGetAll() throws Exception {
    when(todoService.getAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/todo");
    MockMvcBuilders.standaloneSetup(todoController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link TodoController#getAll()}
   */
  @Test
  void testGetAll2() throws Exception {
    when(todoService.getAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/todo");
    getResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(todoController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link TodoController#get(long)}
   */
  @Test
  void testGet() throws Exception {
    when(todoService.get(anyLong())).thenReturn(new TodoVO());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/todo/{id}", 123L);
    MockMvcBuilders.standaloneSetup(todoController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"id\":0,\"title\":null,\"description\":null,\"icon\":null,\"deadline\":null,\"reminder\":null,\"createdAt\":null"
                    + ",\"updatedAt\":null,\"userId\":0}"));
  }

  /**
   * Method under test: {@link TodoController#get(long)}
   */
  @Test
  void testGet2() throws Exception {
    when(todoService.getAll()).thenReturn(new ArrayList<>());
    when(todoService.get(anyLong())).thenReturn(new TodoVO());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/todo/{id}", "", "Uri Vars");
    MockMvcBuilders.standaloneSetup(todoController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link TodoController#update(long, UpdateTodoVO)}
   */
  @Test
  void testUpdate() throws Exception {
    when(todoService.update((UpdateTodoVO) any())).thenReturn(new TodoVO());

    UpdateTodoVO updateTodoVO = new UpdateTodoVO();
    updateTodoVO.setDeadline(null);
    updateTodoVO.setDescription("The characteristics of someone or something");
    updateTodoVO.setIcon("Icon");
    updateTodoVO.setId(123L);
    updateTodoVO.setTitle("Dr");
    String content = (new ObjectMapper()).writeValueAsString(updateTodoVO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/todo/{id}", 123L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(todoController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"id\":0,\"title\":null,\"description\":null,\"icon\":null,\"deadline\":null,\"reminder\":null,\"createdAt\":null"
                    + ",\"updatedAt\":null,\"userId\":0}"));
  }

  /**
   * Method under test: {@link TodoController#create(NewTodoVO)}
   */
  @Test
  void testCreate() throws Exception {
    when(todoService.getAll()).thenReturn(new ArrayList<>());

    NewTodoVO newTodoVO = new NewTodoVO();
    newTodoVO.setDeadline(null);
    newTodoVO.setDescription("The characteristics of someone or something");
    newTodoVO.setIcon("Icon");
    newTodoVO.setReminder(null);
    newTodoVO.setTitle("Dr");
    String content = (new ObjectMapper()).writeValueAsString(newTodoVO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/todo")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(todoController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link TodoController#delete(long)}
   */
  @Test
  void testDelete() throws Exception {
    when(todoService.delete(anyLong())).thenReturn(true);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/todo/{id}", 123L);
    MockMvcBuilders.standaloneSetup(todoController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string("Item has been successfully deleted."));
  }

  /**
   * Method under test: {@link TodoController#delete(long)}
   */
  @Test
  void testDelete2() throws Exception {
    when(todoService.delete(anyLong())).thenReturn(true);
    MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/todo/{id}", 123L);
    deleteResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(todoController)
        .build()
        .perform(deleteResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string("Item has been successfully deleted."));
  }
}

