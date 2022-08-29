package com.tanvoid0.tanspring.models.user.career.volunteer;

import com.tanvoid0.tanspring.models.user.career.Career;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findByCareer(Career career);
}
