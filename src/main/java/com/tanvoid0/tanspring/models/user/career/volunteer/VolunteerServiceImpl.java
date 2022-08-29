package com.tanvoid0.tanspring.models.user.career.volunteer;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.career.CareerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service("volunteerService")
public class VolunteerServiceImpl implements VolunteerService {

  private final VolunteerRepository repository;

  private final CareerService careerService;

  private final ModelMapper mapper;

  public VolunteerServiceImpl(VolunteerRepository repository, CareerService careerService, ModelMapper mapper) {
    this.repository = repository;
    this.careerService = careerService;
    this.mapper = mapper;
  }

  @Override
  public List<VolunteerVO> get() {
    final Career career = careerService.findOrCreateByUser();
    final List<Volunteer> list = repository.findByCareer(career);
    return list.stream().map(this::convertEntityToVO).toList();
  }

  @Override
  public VolunteerVO get(final long id) {
    return convertEntityToVO(findEntity(id));
  }

  @PostMapping
  @Override
  public VolunteerVO add(@Valid @RequestBody final NewVolunteerVO newVolunteerVO) {
    final Career career = careerService.findOrCreateByUser();
    Volunteer entity = mapper.map(newVolunteerVO, Volunteer.class);
    entity.setCareer(career);
    final Volunteer savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<VolunteerVO> add(List<NewVolunteerVO> newVolunteerVOS) {
    return newVolunteerVOS.stream().map(this::add).toList();
  }

  @PutMapping("/{id}")
  @Override
  public VolunteerVO update(UpdateVolunteerVO updateVolunteerVO) {
    final Volunteer entity = findEntity(updateVolunteerVO.getId());
    mapper.map(updateVolunteerVO, entity);
    final Volunteer savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public boolean delete(long id) {
    findEntity(id);
    repository.deleteById(id);
    return true;
  }

  @Override
  public Volunteer findEntity(long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", id));
  }

  @Override
  public VolunteerVO convertEntityToVO(Volunteer volunteer) {
    return mapper.map(volunteer, VolunteerVO.class);
  }

  @Override
  public Volunteer convertVOToEntity(VolunteerVO volunteerVO) {
    return mapper.map(volunteerVO, Volunteer.class);
  }
}
