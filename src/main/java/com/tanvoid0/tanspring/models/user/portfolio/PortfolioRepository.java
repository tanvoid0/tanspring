package com.tanvoid0.tanspring.models.user.portfolio;

import com.tanvoid0.tanspring.models.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
  Optional<Portfolio> findByUser(User user);
}
