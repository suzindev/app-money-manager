package com.suzintech.domain;

import com.suzintech.utils.InstantUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActivityTest {

    @Test
    public void givenValidParams_whenBuildingNewActivity_thenReturnNewValidActivity() {
        final var description = "Description";
        final var value = 3.55f;
        final var date = InstantUtils.now();
        final var type = Type.ActivityType.REVENUE;

        final var activity = Activity.newActivity(date, description, value, type);

        Assertions.assertNotNull(activity);
        Assertions.assertNotNull(activity.getId());
        Assertions.assertEquals(36, activity.getId().length());
        Assertions.assertEquals(description, activity.getDescription());
        Assertions.assertEquals(date, activity.getDate());
        Assertions.assertEquals(value, activity.getValue(), 0.001);
        Assertions.assertEquals(type, activity.getType());
        Assertions.assertNotNull(activity.getCreatedAt());
        Assertions.assertNotNull(activity.getUpdatedAt());
    }
}
