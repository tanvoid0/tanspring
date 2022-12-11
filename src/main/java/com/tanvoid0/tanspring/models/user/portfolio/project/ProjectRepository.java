package com.tanvoid0.tanspring.models.user.portfolio.project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
  List<Project> findByPortfolio_Id(long portfolioId);
}
