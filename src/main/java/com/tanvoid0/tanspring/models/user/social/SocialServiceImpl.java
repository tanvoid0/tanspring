package com.tanvoid0.tanspring.models.user.social;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.security.auth.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
