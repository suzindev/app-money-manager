package com.suzintech.infra.usecase;

import com.suzintech.application.ActivityGateway;
import com.suzintech.domain.Activity;
import com.suzintech.exception.PersistenceException;
import com.suzintech.infra.persistence.ActivityEntity;
import com.suzintech.infra.persistence.ActivityRepository;
import jakarta.persistence.OptimisticLockException;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityUseCase implements ActivityGateway {

    private final ActivityRepository activityRepository;

    private ActivityUseCase(final ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public static ActivityUseCase build(final ActivityRepository activityRepository) {
        return new ActivityUseCase(activityRepository);
    }

    @Override
    public void create(final Activity activity) {
        try {
            this.activityRepository.save(ActivityEntity.from(activity));
        } catch (IllegalArgumentException | OptimisticLockException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void delete(final String id) {
        try {
            this.activityRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Activity> findAll() {
        return this.activityRepository.findAll().stream()
                .map(ActivityEntity::toModel)
                .collect(Collectors.toList());
    }
}
