package com.suzintech.infra.service.impl;

import com.suzintech.application.ActivityGateway;
import com.suzintech.domain.Type;
import com.suzintech.infra.dto.InsertActivityInput;
import com.suzintech.infra.dto.InsertActivityOutput;
import com.suzintech.infra.dto.ListActivitiesOutput;
import com.suzintech.infra.mapper.ActivityToInsertActivityOutputMapper;
import com.suzintech.infra.mapper.ActivityToListActivityOutputMapper;
import com.suzintech.infra.mapper.InsertActivityInputToActivityMapper;
import com.suzintech.infra.service.ActivityService;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityServiceImpl implements ActivityService {

    private final ActivityGateway activityGateway;

    private ActivityServiceImpl(final ActivityGateway activityGateway) {
        this.activityGateway = activityGateway;
    }

    public static ActivityServiceImpl build(final ActivityGateway activityGateway) {
        return new ActivityServiceImpl(activityGateway);
    }

    @Override
    public InsertActivityOutput insert(final InsertActivityInput input) {
        final var activity = InsertActivityInputToActivityMapper.build().apply(input);

        this.activityGateway.create(activity);

        return ActivityToInsertActivityOutputMapper.build().apply(activity);
    }

    @Override
    public void remove(final String id) {
        this.activityGateway.delete(id);
    }

    @Override
    public List<ListActivitiesOutput> list() {
        final var list = this.activityGateway.findAll();

        return list.stream()
                .map(m -> ActivityToListActivityOutputMapper.build().apply(m))
                .collect(Collectors.toList());
    }

    @Override
    public float calculateBalance() {
        final var list = this.activityGateway.findAll();

        if (list == null || list.isEmpty()) {
            return 0;
        }

        return (float) list.stream()
                .mapToDouble(m -> m.getType() == Type.ActivityType.REVENUE ? m.getValue() : -m.getValue())
                .sum();

    }
}
