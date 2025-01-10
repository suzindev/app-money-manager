package com.suzintech.infra.mapper;

import com.suzintech.infra.controller.dto.ActivityDTO;
import com.suzintech.infra.controller.dto.ListActivitiesResponse;
import com.suzintech.infra.dto.ListActivitiesOutput;

import java.util.List;
import java.util.function.Function;

public class ListActivitiesToListActivitiesResponseMapper implements Function<List<ListActivitiesOutput>, ListActivitiesResponse> {

    public static ListActivitiesToListActivitiesResponseMapper build() {
        return new ListActivitiesToListActivitiesResponseMapper();
    }

    @Override
    public ListActivitiesResponse apply(final List<ListActivitiesOutput> outputs) {
        final var list = outputs.stream()
                .map(m -> new ActivityDTO(
                        m.id(),
                        m.date(),
                        m.description(),
                        m.value(),
                        m.type()
                )).toList();

        return new ListActivitiesResponse(list);
    }
}
