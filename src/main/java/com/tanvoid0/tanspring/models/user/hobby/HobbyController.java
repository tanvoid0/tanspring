package com.tanvoid0.tanspring.models.user.hobby;

import com.tanvoid0.tanspring.common.template.CustomController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hobby")
@Slf4j
public class HobbyController implements CustomController<HobbyVO, NewHobbyVO, UpdateHobbyVO> {

    private final HobbyService service;

    public HobbyController(HobbyService service) {
        this.service = service;
    }

    @GetMapping
    @Override
    public List<HobbyVO> get() {
        log.info("Find all hobbies");
        return service.get();
    }

    @GetMapping("/{id}")
    @Override
    public HobbyVO get(@PathVariable("id") final long id) {
        log.info("Find hobby by id {}", id);
        return service.get(id);
    }

    @PostMapping
    @Override
    @PreAuthorize("hasRole('USER')")
    public HobbyVO add(@Valid @RequestBody final NewHobbyVO newVO) {
        log.info("Add new hobby {}", newVO);
        return service.add(newVO);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    @Override
    public HobbyVO update(@PathVariable final long id, @Valid @RequestBody final UpdateHobbyVO updateVO) {
        log.info("Update hobby");
        return service.update(updateVO);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean delete(@PathVariable long id) {
        log.info("Delete hobby");
        return service.delete(id);
    }
}
