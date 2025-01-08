package com.suzintech.domain;

import com.suzintech.exception.DomainException;
import com.suzintech.utils.InstantUtils;

import java.time.Instant;
import java.util.UUID;

public class Activity {

    private String id;
    private Instant date;
    private String description;
    private float value;
    private Type.ActivityType type;
    private Instant createdAt;
    private Instant updatedAt;

    private Activity(final String id, final Instant date, final String description,
                     final float value, final Type.ActivityType type, final Instant createdAt, final Instant updatedAt) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.value = value;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.validate();
    }

    public static Activity newActivity(final Instant date, final String description,
                                       final float value, final Type.ActivityType type) {
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
                                final float value, final Type.ActivityType type, final Instant createdAt, final Instant updatedAt) {
        return new Activity(id, date, description, value, type, createdAt, updatedAt);
    }

    private void validate() {
        if (this.id.isBlank()) {
            throw new DomainException("Activity's ID should not be blank");
        } else if (this.id.length() != 36) {
            throw new DomainException("Activity's ID should be a valid UUID");
        } else if (this.description.isBlank()) {
            throw new DomainException("Activity's description should not be blank");
        } else if (this.type != Type.ActivityType.EXPENSE && this.type != Type.ActivityType.REVENUE) {
            throw new DomainException("Activity's type shouldbe either expense or revenue");
        } else if (this.value < 0) {
            throw new DomainException("Activity's value should be greater than zero");
        } else if (this.createdAt.isAfter(this.updatedAt)) {
            throw new DomainException("Activity's created at should be before updated at");
        }
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

    public Type.ActivityType getType() {
        return type;
    }

    public void setType(Type.ActivityType type) {
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
