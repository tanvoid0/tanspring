package com.tanvoid0.tanspring.models.user.hobby;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.common.vo.SwapOrderSequence;
import com.tanvoid0.tanspring.models.user.User;
import com.tanvoid0.tanspring.models.user.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service("hobbyService")
public class HobbyServiceImpl implements HobbyService {

  @Autowired
  private HobbyRepository repository;

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper mapper;

  @Override
  public HobbyVO add(NewHobbyVO newVO) {
    Hobby entity = convertVOToEntity(newVO);
    entity.setUser(userService.getAuthUser());
    final Hobby savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public HobbyVO add(final String username, final NewHobbyVO newVO) {
    final User user = userService.findByUsername(username);

    final Hobby entity = convertVOToEntity(newVO);
    entity.setUser(user);

    final Hobby savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<HobbyVO> add(List<NewHobbyVO> newHobbyVOS) {
    return newHobbyVOS.stream().map(this::add).toList();
  }

  @Override
  public List<HobbyVO> get() {
    final List<Hobby> hobbies = repository.findAllByUserIdOrderByOrderSeq(userService.getAuthUserId());
    return hobbies.stream().map(this::convertEntityToVO).toList();
  }

  @Override
  public HobbyVO get(long id) {
    return convertEntityToVO(findEntity(id));
  }

  @Override
  public List<HobbyVO> findByUsername(final String username) {
    final User user = userService.findByUsername(username);
    final List<Hobby> hobbies = repository.findAllByUserIdOrderByOrderSeq(user.getId());
    return hobbies.stream().map(this::convertEntityToVO).toList();
  }

  @Override
  public List<HobbyVO> swap(final SwapOrderSequence swapOrderSequence) {
    final Hobby entity1 = findEntity(swapOrderSequence.getId1());
    final Hobby entity2 = findEntity(swapOrderSequence.getId2());
    final long seq1 = entity1.getOrderSeq();
    final long seq2 = entity2.getOrderSeq();

    entity1.setOrderSeq(seq2);
    entity2.setOrderSeq(seq1);
    final Hobby savedEntity1 = repository.save(entity1);
    final Hobby savedEntity2 = repository.save(entity2);

    return Stream.of(savedEntity1, savedEntity2).map(this::convertEntityToVO).toList();
  }

  @Override
  public HobbyVO update(UpdateHobbyVO updateVO) {
    final Hobby entity = findEntity(updateVO.getId());
    mapper.map(updateVO, entity);
    final Hobby savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public boolean delete(long id) {
    findEntity(id);
    repository.deleteById(id);
    return true;
  }

  @Override
  public Hobby findEntity(final long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hobby", "id", id));
  }

  private HobbyVO convertEntityToVO(final Hobby hobby) {
    return mapper.map(hobby, HobbyVO.class);
  }

  private Hobby convertVOToEntity(final HobbyVO hobbyVO) {
    return mapper.map(hobbyVO, Hobby.class);
  }

  private Hobby convertVOToEntity(final NewHobbyVO newVO) {
    return mapper.map(newVO, Hobby.class);
  }
}
