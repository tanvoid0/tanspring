package com.tanvoid0.tanspring.models.user.career;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.User;
import com.tanvoid0.tanspring.models.user.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("careerService")
public class CareerServiceImpl implements CareerService {

  final UserService userService;
  final CareerRepository repository;

  final ModelMapper mapper;

  public CareerServiceImpl(UserService userService, CareerRepository repository, ModelMapper mapper) {
    this.userService = userService;
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<CareerVO> get() {
    return null;
  }

  @Override
  public CareerVO getCareer() {
    return this.convertEntityToVO(this.findOrCreateByUser());
  }

  @Override
  public CareerVO get(long id) {
    return convertEntityToVO(findEntity(id));
  }

  @Override
  public CareerVO add(NewCareerVO newCareerVO) {
    return null;
  }

  @Override
  public List<CareerVO> add(List<NewCareerVO> newCareerVOS) {
    return null;
  }

  @Override
  public CareerVO update(UpdateCareerVO updateCareerVO) {
    return null;
  }

  @Override
  public boolean delete(long id) {
    return false;
  }

  @Override
  public Career findEntity(long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Career", "id", id));
  }

  @Override
  public CareerVO convertEntityToVO(Career career) {
    return mapper.map(career, CareerVO.class);
  }

  @Override
  public Career convertVOToEntity(CareerVO careerVO) {
    return mapper.map(careerVO, Career.class);
  }

  @Override
  public Career findOrCreateByUser() {
    final User user = userService.getAuthUser();
    return this.findOrCreateByUser(user);
  }

  @Override
  public Career findOrCreateByUser(final User user) {
    return repository.findByUser(user).orElseGet(() -> repository.save(Career.builder().user(user).build()));
  }

  @Override
  public CareerVO getByUsername(String username) {
    final User user = mapper.map(userService.get(username), User.class);
    final Career career = repository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Career", "userId", user.getId()));
    return convertEntityToVO(career);
  }
}
