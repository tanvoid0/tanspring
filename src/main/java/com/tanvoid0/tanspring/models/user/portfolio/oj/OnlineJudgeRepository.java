package com.tanvoid0.tanspring.models.user.portfolio.oj;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OnlineJudgeRepository extends JpaRepository<OnlineJudge, Long> {
    List<OnlineJudge> findByPortfolio_Id(long id);
}
