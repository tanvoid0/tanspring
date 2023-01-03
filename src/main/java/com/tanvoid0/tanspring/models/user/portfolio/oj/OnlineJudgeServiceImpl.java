package com.tanvoid0.tanspring.models.user.portfolio.oj;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("onlineJudgeService")
public class OnlineJudgeServiceImpl implements OnlineJudgeService {

    @Autowired
    private OnlineJudgeRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public OnlineJudgeVO get(long id) {
        return convertEntityToVO(findById(id));
    }

    @Override
    public List<OnlineJudgeVO> get() {
        final List<OnlineJudge> ojs = repository.findAll();
        return ojs.stream().map(this::convertEntityToVO).toList();
    }

    @Override
    public OnlineJudgeVO add(NewOnlineJudgeVO newVO) {
        return null;
    }

    @Override
    public List<OnlineJudgeVO> add(List<NewOnlineJudgeVO> newVO) {
        return null;
    }

    @Override
    public OnlineJudgeVO update(UpdateOnlineJudgeVO updateVO) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        findById(id);
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        repository.deleteAll();
        return true;
    }

    @Override
    public List<OnlineJudgeVO> getByPortfolioId(long id) {
        List<OnlineJudge> ojs = repository.findByPortfolio_Id(id);
        return ojs.stream().map(this::convertEntityToVO).toList();
    }


    private OnlineJudge findById(final long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OnlineJudge", "id", id));
    }

    private OnlineJudgeVO convertEntityToVO(final OnlineJudge entity) {
        return mapper.map(entity, OnlineJudgeVO.class);
    }

    private OnlineJudge convertVOToEntity(final OnlineJudgeVO vo) {
        return mapper.map(vo, OnlineJudge.class);
    }
}
