package com.suzintech.infra.mapper;

import com.suzintech.infra.dto.ActivityResponse;
import com.suzintech.infra.dto.InsertActivityOutput;

import java.util.function.Function;

public class ActivityOutputToActivityResponseMapper implements Function<InsertActivityOutput, ActivityResponse> {

    public static ActivityOutputToActivityResponseMapper build() {
        return new ActivityOutputToActivityResponseMapper();
    }

    @Override
    public ActivityResponse apply(InsertActivityOutput output) {
        return new ActivityResponse(
                output.id(),
                output.date(),
                output.description(),
                output.value(),
                output.type()
        );
    }
}
