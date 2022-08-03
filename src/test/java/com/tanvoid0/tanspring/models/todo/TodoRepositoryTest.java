package com.tanvoid0.tanspring.models.todo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@ContextConfiguration(classes = {TodoRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.tanvoid0.tanspring.models.todo"})
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
@Transactional
class TodoRepositoryTest {

  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  private DataSource dataSource;
  @Autowired
  private JdbcTemplate template;
  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  private TodoRepository repository;

  @Test
  void injectedComponentAreNotNull() {
    assertThat(dataSource).isNotNull();
    assertThat(template).isNotNull();
    assertThat(repository).isNotNull();
    assertThat(entityManager).isNotNull();
  }

  @Test
  void findAllByUserId() {
    // given
    Date now = Calendar.getInstance().getTime();
    Todo todo1 = Todo.builder().title("Task 1").userId(-1L).createdAt(now).build();
    Todo todo2 = Todo.builder().title("Task 2").userId(-1L).createdAt(now).build();
    entityManager.persist(todo1);
    entityManager.persist(todo2);

    // when
    List<Todo> result = repository.findAllByUserId(-1L);

    // then
    assertThat(result.size()).isEqualTo(2);
    assertThat(result.get(0)).isEqualTo(todo1);
    assertThat(result.get(1)).isEqualTo(todo2);
  }

  /**
   * Method under test: {@link TodoRepository#findAllByUserId(long)}
   */
  @Test
  void testFindAllByUserId() {
    Todo todo = new Todo();
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    todo.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
    todo.setDeadline(null);
    todo.setDescription("The characteristics of someone or something");
    todo.setIcon("Icon");
    todo.setReminder(null);
    todo.setTitle("Dr");
    LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
    todo.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
    todo.setUserId(123L);

    Todo todo1 = new Todo();
    LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
    todo1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
    todo1.setDeadline(null);
    todo1.setDescription("The characteristics of someone or something");
    todo1.setIcon("Icon");
    todo1.setReminder(null);
    todo1.setTitle("Dr");
    LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
    todo1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
    todo1.setUserId(123L);
    todoRepository.save(todo);
    todoRepository.save(todo1);
    assertTrue(todoRepository.findAllByUserId(1L).isEmpty());
  }
}