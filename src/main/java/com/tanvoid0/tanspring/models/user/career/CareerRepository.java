package com.tanvoid0.tanspring.models.user.career;

import com.tanvoid0.tanspring.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {
    Optional<Career> findByUser(User user);
}
