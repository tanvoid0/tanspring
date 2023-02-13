package com.tanvoid0.tanspring.models.user.skill;

import com.tanvoid0.tanspring.models.user.AppUser;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
  Optional<SkillEntity> findByUser(AppUser user);
}
