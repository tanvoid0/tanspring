package com.tanvoid0.tanspring.models.comment;

import static org.assertj.core.api.Assertions.assertThat;

import com.tanvoid0.tanspring.models.post.Post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class CommentRepositoryTest {

  @Autowired
  private CommentRepository repository;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
    repository.deleteAll();
  }

  @Test
  void findByPostId() {
    // given
    Post post = Post.builder()
        .id(-1L)
        .title("post1")
        .description("post description")
        .content("post content")
        .build();

    Comment comment = Comment.builder().id(-1L).name("test").post(post).build();
    repository.save(comment);

    // when
    List<Comment> result = repository.findByPostId(-1L);

    // then
    assertThat(result.size()).isEqualTo(1);
  }
}