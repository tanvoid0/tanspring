package com.tanvoid0.tanspring.models.user.career.experience;

import com.tanvoid0.tanspring.models.user.career.Career;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByCareer(Career career);
}
