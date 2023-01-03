package com.tanvoid0.tanspring.models.user.portfolio.oj;

import java.util.List;

public interface OnlineJudgeService {
    OnlineJudgeVO get(long id);

    List<OnlineJudgeVO> get();

    OnlineJudgeVO add(NewOnlineJudgeVO newVO);

    List<OnlineJudgeVO> add(List<NewOnlineJudgeVO> newVO);

    OnlineJudgeVO update(UpdateOnlineJudgeVO updateVO);
    boolean delete(long id);

    boolean deleteAll();

    List<OnlineJudgeVO> getByPortfolioId(long id);
}
