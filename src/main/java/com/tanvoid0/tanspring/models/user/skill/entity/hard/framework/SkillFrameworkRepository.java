package com.tanvoid0.tanspring.models.user.skill.entity.hard.framework;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SkillFrameworkRepository extends JpaRepository<SkillFramework, Long> {
  Set<SkillFramework> findBySkillId(long id);
}
