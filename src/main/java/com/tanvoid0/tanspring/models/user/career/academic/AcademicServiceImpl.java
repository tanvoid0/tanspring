package com.tanvoid0.tanspring.models.user.career.academic;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.career.CareerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("academicService")
public class AcademicServiceImpl implements AcademicService {

  private final CareerService careerService;
  private final AcademicRepository repository;
  private final ModelMapper mapper;


  public AcademicServiceImpl(CareerService careerService, AcademicRepository repository, ModelMapper mapper) {
    this.careerService = careerService;
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<AcademicVO> get() {
    final Career career = careerService.findOrCreateByUser();
    final List<Academic> list = repository.findByCareer(career);
    return list.stream().map(this::convertEntityToVO).toList();
  }

  @Override
  public AcademicVO get(final long id) {
    return convertEntityToVO(findEntity(id));
  }

  @Override
  public AcademicVO add(final NewAcademicVO newAcademicVO) {
    final Career career = careerService.findOrCreateByUser();
    Academic entity = mapper.map(newAcademicVO, Academic.class);
    entity.setCareer(career);
    final Academic savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<AcademicVO> add(final List<NewAcademicVO> newAcademicVOS) {
    return newAcademicVOS.stream().map(this::add).toList();
  }

  @Override
  public AcademicVO update(final UpdateAcademicVO updateAcademicVO) {
    final Academic entity = findEntity(updateAcademicVO.getId());
    mapper.map(updateAcademicVO, entity);
    final Academic academic = repository.save(entity);
    return convertEntityToVO(academic);
  }

  @Override
  public boolean delete(final long id) {
    findEntity(id);
    repository.deleteById(id);
    return true;
  }

  @Override
  public Academic findEntity(final long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Academic", "id", id));
  }

  @Override
  public AcademicVO convertEntityToVO(final Academic academic) {
    return mapper.map(academic, AcademicVO.class);
  }

  @Override
  public Academic convertVOToEntity(final AcademicVO academicVO) {
    return mapper.map(academicVO, Academic.class);
  }
}
