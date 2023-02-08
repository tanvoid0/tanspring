package com.tanvoid0.tanspring.models.user.social;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.common.vo.SwapOrderSequence;
import com.tanvoid0.tanspring.models.user.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service("socialService")
public class SocialServiceImpl implements SocialService {

  private final SocialRepository repository;

  private final UserService userService;

  private final ModelMapper mapper;

  public SocialServiceImpl(SocialRepository repository, UserService userService, ModelMapper mapper) {
    this.repository = repository;
    this.userService = userService;
    this.mapper = mapper;
  }

  @Override
  public List<SocialVO> get() {
    final List<Social> socials = repository.findAllByUserId(userService.getAuthUserId());
    return socials.stream().map(this::convertEntityToVO).toList();
  }

  @Override
  public SocialVO get(long id) {
    return convertEntityToVO(findEntity(id));
  }

  @Override
  public SocialVO add(NewSocialVO newSocialVO) {
    Social entity = mapper.map(newSocialVO, Social.class);
    entity.setUser(userService.getAuthUser());
    final Social savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<SocialVO> add(List<NewSocialVO> newSocialVOS) {
    return newSocialVOS.stream().map(this::add).toList();
  }

  @Override
  public SocialVO update(UpdateSocialVO updateSocialVO) {
    final Social entity = findEntity(updateSocialVO.getId());
    mapper.map(updateSocialVO, entity);
    final Social social = repository.save(entity);
    return convertEntityToVO(social);
  }

  @Override
  public boolean delete(long id) {
    findEntity(id);
    repository.deleteById(id);
    return true;
  }

  @Override
  public Social findEntity(long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Social", "id", id));
  }

  @Override
  public SocialVO convertEntityToVO(Social social) {
    return mapper.map(social, SocialVO.class);
  }

  @Override
  public Social convertVOToEntity(SocialVO socialVO) {
    return mapper.map(socialVO, Social.class);
  }

  @Override
  public List<SocialVO> swap(SwapOrderSequence swapOrderSequence) {
    final Social entity1 = findEntity(swapOrderSequence.getId1());
    final Social entity2 = findEntity(swapOrderSequence.getId2());

    final long seq1 = entity1.getOrderSeq();
    final long seq2 = entity2.getOrderSeq();

    entity1.setOrderSeq(seq2);
    entity2.setOrderSeq(seq1);

    final Social savedEntity1 = repository.save(entity1);
    final Social savedEntity2 = repository.save(entity2);

    return Stream.of(savedEntity1, savedEntity2).map(this::convertEntityToVO).toList();
  }
}
