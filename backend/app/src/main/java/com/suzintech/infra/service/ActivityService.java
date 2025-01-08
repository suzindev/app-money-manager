package com.suzintech.infra.service;

import com.suzintech.infra.dto.InsertActivityInput;
import com.suzintech.infra.dto.InsertActivityOutput;
import com.suzintech.infra.dto.ListActivitiesOutput;

import java.util.List;

public interface ActivityService {

    InsertActivityOutput insert(final InsertActivityInput input);

    void remove(final String id);

    List<ListActivitiesOutput> list();

    float calculateBalance();
}
