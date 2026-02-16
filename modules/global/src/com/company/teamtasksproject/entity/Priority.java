package com.company.teamtasksproject.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Priority implements EnumClass<String> {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private String id;

    Priority(String value) {
        this.id = value;
    }

    @Override
    public String getId() {
        return id;
    }

    @Nullable
    public static Priority fromId(String id) {
        for (Priority at : Priority.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}