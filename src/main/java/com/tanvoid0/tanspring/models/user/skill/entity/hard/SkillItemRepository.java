package com.tanvoid0.tanspring.models.user.skill.entity.hard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SkillItemRepository extends JpaRepository<SkillItem, Long> {
  Set<SkillItem> findBySkillId(long id);
}
