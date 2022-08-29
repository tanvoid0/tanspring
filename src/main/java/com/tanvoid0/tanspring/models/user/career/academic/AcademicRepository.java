package com.tanvoid0.tanspring.models.user.career.academic;

import com.tanvoid0.tanspring.models.user.career.Career;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicRepository extends JpaRepository<Academic, Long> {
    List<Academic> findByCareer(Career career);
}
