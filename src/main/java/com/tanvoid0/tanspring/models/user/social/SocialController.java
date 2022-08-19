package com.tanvoid0.tanspring.models.user.social;

import com.tanvoid0.tanspring.common.template.CustomController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/social")
@Slf4j
public class SocialController implements CustomController<SocialVO, NewSocialVO, UpdateSocialVO> {
    private final SocialService service;

    public SocialController(SocialService service) {
        this.service = service;
    }

    @GetMapping
    @Override
    public List<SocialVO> get() {
        return service.get();
    }

    @GetMapping("/{id}")
    @Override
    public SocialVO get(final @PathVariable("id") long id) {
        return service.get(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Override
    public SocialVO add(@Valid @RequestBody final NewSocialVO newSocialVO) {
        return service.add(newSocialVO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Override
    public SocialVO update(@PathVariable("id") final long id, @Valid @RequestBody UpdateSocialVO updateSocialVO) {
        updateSocialVO.setId(id);
        return service.update(updateSocialVO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Override
    public boolean delete(@PathVariable("id") long id) {
        return service.delete(id);
    }
}
