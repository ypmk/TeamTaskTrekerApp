package com.company.teamtasksproject.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "TEAMTASKSPROJECT_TASK_COMMENT")
@Entity(name = "teamtasksproject_TaskComment")
public class TaskComment extends StandardEntity {
    private static final long serialVersionUID = -533875408619133958L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TASK_ID")
    private Task task;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTHOR_ID")
    private User author;

    @NotNull
    @Lob
    @Column(name = "TEXT", nullable = false)
    private String text;

    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }

    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text;}

}