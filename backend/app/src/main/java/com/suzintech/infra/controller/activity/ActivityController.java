package com.suzintech.infra.controller.activity;

import com.suzintech.infra.dto.ActivityRequest;
import com.suzintech.infra.dto.ActivityResponse;
import com.suzintech.infra.dto.CalculateBalanceResponse;
import com.suzintech.infra.dto.ListActivitiesResponse;
import com.suzintech.infra.mapper.ActivityOutputToActivityResponseMapper;
import com.suzintech.infra.mapper.ActivityRequestToActivityServiceMapper;
import com.suzintech.infra.mapper.ListActivitiesToListActivitiesResponseMapper;
import com.suzintech.infra.persistence.ActivityRepository;
import com.suzintech.infra.service.impl.ActivityServiceImpl;
import com.suzintech.infra.usecase.ActivityUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityRepository repository;

    public ActivityController(ActivityRepository activityRepository) {
        this.repository = activityRepository;
    }

    @GetMapping
    public ResponseEntity<ListActivitiesResponse> listActivities() {
        final var gateway = ActivityUseCase.build(repository);
        final var service = ActivityServiceImpl.build(gateway);
        final var list = service.list();
        final var response = ListActivitiesToListActivitiesResponseMapper.build().apply(list);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ActivityResponse> insertActivities(@RequestBody ActivityRequest request) {
        final var gateway = ActivityUseCase.build(repository);
        final var service = ActivityServiceImpl.build(gateway);
        final var input = ActivityRequestToActivityServiceMapper.build().apply(request);
        final var output = service.insert(input);
        final var response = ActivityOutputToActivityResponseMapper.build().apply(output);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivityById(@PathVariable("id") final String id) {
        final var gateway = ActivityUseCase.build(repository);
        final var service = ActivityServiceImpl.build(gateway);

        service.remove(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance")
    public ResponseEntity<CalculateBalanceResponse> calculateBalance() {
        final var gateway = ActivityUseCase.build(repository);
        final var service = ActivityServiceImpl.build(gateway);
        final var output = service.calculateBalance();
        final var response = new CalculateBalanceResponse(output);

        return ResponseEntity.ok().body(response);
    }
}
