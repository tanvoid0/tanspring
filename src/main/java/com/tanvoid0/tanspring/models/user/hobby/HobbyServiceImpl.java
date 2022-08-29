package com.tanvoid0.tanspring.models.user.hobby;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    Hobby entity = mapper.map(newVO, Hobby.class);
    entity.setUser(userService.getAuthUser());
    final Hobby savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<HobbyVO> add(List<NewHobbyVO> newHobbyVOS) {
    return newHobbyVOS.stream().map(this::add).toList();
  }

  @Override
  public List<HobbyVO> get() {
    final List<Hobby> hobbies = repository.findAllByUserId(userService.getAuthUserId());
    return hobbies.stream().map(this::convertEntityToVO).toList();
  }

  @Override
  public HobbyVO get(long id) {
    return convertEntityToVO(findEntity(id));
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

  @Override
  public HobbyVO convertEntityToVO(final Hobby hobby) {
    return mapper.map(hobby, HobbyVO.class);
  }

  @Override
  public Hobby convertVOToEntity(final HobbyVO hobbyVO) {
    return mapper.map(hobbyVO, Hobby.class);
  }
}
