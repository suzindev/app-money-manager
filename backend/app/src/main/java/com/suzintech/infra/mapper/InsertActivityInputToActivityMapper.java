package com.suzintech.infra.mapper;

import com.suzintech.domain.Activity;
import com.suzintech.domain.Type;
import com.suzintech.exception.ServiceException;
import com.suzintech.infra.dto.InsertActivityInput;

import java.util.function.Function;

public class InsertActivityInputToActivityMapper implements Function<InsertActivityInput, Activity> {

    public static InsertActivityInputToActivityMapper build() {
        return new InsertActivityInputToActivityMapper();
    }

    @Override
    public Activity apply(final InsertActivityInput input) {
        if (input.type().trim().toUpperCase().equals(Type.ActivityType.REVENUE.name())) {
            return Activity.newActivity(
                    input.date(),
                    input.description(),
                    input.value(),
                    Type.ActivityType.REVENUE);
        } else if (input.type().trim().toUpperCase().equals(Type.ActivityType.EXPENSE.name())) {
            return Activity.newActivity(
                    input.date(),
                    input.description(),
                    input.value(),
                    Type.ActivityType.EXPENSE);
        } else {
            throw new ServiceException("Invalid activity type");
        }
    }
}
