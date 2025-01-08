package com.suzintech.domain;

import com.suzintech.utils.InstantUtils;

import java.time.Instant;
import java.util.UUID;

public class Activity {

    private String id;
    private Instant date;
    private String description;
    private float value;
    private Type type;
    private Instant createdAt;
    private Instant updatedAt;

    private Activity(final String id, final Instant date, final String description,
                     final float value, final Type type, final Instant createdAt, final Instant updatedAt) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.value = value;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Activity newActivity(final Instant date, final String description,
                                       final float value, final Type type) {
        return new Activity(
                UUID.randomUUID().toString().toLowerCase(),
                date,
                description,
                value,
                type,
                InstantUtils.now(),
                InstantUtils.now()
        );
    }

    public static Activity with(final String id, final Instant date, final String description,
                                final float value, final Type type, final Instant createdAt, final Instant updatedAt) {
        return new Activity(id, date, description, value, type, createdAt, updatedAt);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
