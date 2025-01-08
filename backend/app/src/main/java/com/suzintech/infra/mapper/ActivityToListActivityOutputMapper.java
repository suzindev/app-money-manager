package com.suzintech.infra.mapper;

import com.suzintech.domain.Activity;
import com.suzintech.infra.dto.ListActivitiesOutput;

import java.util.function.Function;

public class ActivityToListActivityOutputMapper implements Function<Activity, ListActivitiesOutput> {

    public static final ActivityToListActivityOutputMapper build() {
        return new ActivityToListActivityOutputMapper();
    }

    @Override
    public ListActivitiesOutput apply(final Activity activity) {
        return new ListActivitiesOutput(
                activity.getId(),
                activity.getDate(),
                activity.getDescription(),
                activity.getValue(),
                activity.getType().getValue()
        );
    }
}
