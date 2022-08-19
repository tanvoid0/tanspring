package com.tanvoid0.tanspring.models.user.social;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialRepository extends JpaRepository<Social, Long> {
    List<Social> findAllByUserId(long userId);
}
