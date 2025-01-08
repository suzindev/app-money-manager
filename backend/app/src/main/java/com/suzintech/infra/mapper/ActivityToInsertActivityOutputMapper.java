package com.suzintech.infra.mapper;

import com.suzintech.domain.Activity;
import com.suzintech.infra.dto.InsertActivityOutput;

import java.util.function.Function;

public class ActivityToInsertActivityOutputMapper implements Function<Activity, InsertActivityOutput> {

    public static final ActivityToInsertActivityOutputMapper build() {
        return new ActivityToInsertActivityOutputMapper();
    }

    @Override
    public InsertActivityOutput apply(final Activity activity) {
        return new InsertActivityOutput(
                activity.getId(),
                activity.getDate(),
                activity.getDescription(),
                activity.getValue(),
                activity.getType().getValue()
        );
    }
}
