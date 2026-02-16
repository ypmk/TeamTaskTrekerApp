package com.company.teamtasksproject.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum TaskStatus implements EnumClass<String> {
    NEW("NEW"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE"),
    CANCELED("CANCELED");

    private String id;

    TaskStatus(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Nullable
    public static TaskStatus fromId(String id) {
        for (TaskStatus at : TaskStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}