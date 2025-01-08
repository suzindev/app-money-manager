package com.suzintech.application;

import com.suzintech.domain.Activity;

import java.util.List;

public interface ActivityGateway {

    void create(final Activity activity);

    void delete(final String id);

    List<Activity> findAll();
}
