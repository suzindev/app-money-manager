package com.suzintech.infra.mapper;

import com.suzintech.infra.dto.ActivityRequest;
import com.suzintech.infra.dto.InsertActivityInput;

import java.util.function.Function;

public class ActivityRequestToActivityServiceMapper implements Function<ActivityRequest, InsertActivityInput> {

    public static ActivityRequestToActivityServiceMapper build() {
        return new ActivityRequestToActivityServiceMapper();
    }

    @Override
    public InsertActivityInput apply(final ActivityRequest request) {
        return new InsertActivityInput(
                request.date(),
                request.description(),
                request.value(),
                request.type()
        );
    }
}
