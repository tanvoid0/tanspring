package com.tanvoid0.tanspring.models.todo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
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

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
@Transactional
class TodoRepositoryTest {

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
    Date now = new Date(2020, Calendar.JANUARY, 1);
    Todo todo1 = Todo.builder().title("Task 1").userId(-1L).createdAt(now).build();
    Todo todo2 = Todo.builder().title("Task 2").userId(-1L).createdAt(now).build();
    entityManager.persist(todo1);
    entityManager.persist(todo2);

    // when
    List<Todo> result = repository.findAllByUserId(-1L);

    // then
    assertThat(result.size()).isEqualTo(2);
  }
}