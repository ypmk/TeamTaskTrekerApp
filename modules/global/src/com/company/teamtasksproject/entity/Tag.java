package com.company.teamtasksproject.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@NamePattern("%s|name")
@Table(name = "TEAMTASKSPROJECT_TAG")
@Entity(name = "teamtasksproject_Tag")
public class Tag extends StandardEntity {
    private static final long serialVersionUID = -8786359267849761252L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}