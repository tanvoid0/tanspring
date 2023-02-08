package com.tanvoid0.tanspring.models.user.hobby;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
  List<Hobby> findAllByUserIdOrderByOrderSeq(long userId);
}
