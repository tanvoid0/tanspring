package com.tanvoid0.tanspring.models.user.career.achievement;

import com.tanvoid0.tanspring.models.user.career.Career;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByCareer(Career career);
}
