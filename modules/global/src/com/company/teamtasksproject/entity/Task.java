package com.company.teamtasksproject.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;


@NamePattern("%s|title")
@Table(name = "TEAMTASKSPROJECT_TASK")
@Entity(name = "teamtasksproject_Task")
public class Task extends StandardEntity {
    private static final long serialVersionUID = -2675192910345128190L;

    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @NotNull
    @Column(name = "STATUS", nullable = false)
    private String status;

    @NotNull
    @Column(name = "PRIORITY", nullable = false)
    private String priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSIGNEE_ID")
    private User assignee;

    @Temporal(TemporalType.DATE)
    @Column(name = "DUE_DATE")
    private Date dueDate;

    @OrderBy("createTs")
    @Composition
    @OneToMany(mappedBy = "task")
    private List<TaskComment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private User author;


    @JoinTable(name = "TEAMTASKSPROJECT_TASK_TEAMTASKSPROJECT_TAG_LINK",
            joinColumns = @JoinColumn(name = "TASK_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID"))

    @ManyToMany
    private Set<Tag> tags;

    public TaskStatus getStatus() {
        return status == null ? null : TaskStatus.fromId(status);
    }

    public void setStatus(TaskStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public Priority getPriority() {
        return priority == null ? null : Priority.fromId(priority);
    }

    public void setPriority(Priority priority) {
        this.priority = priority == null ? null : priority.getId();
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public User getAssignee() { return assignee; }
    public void setAssignee(User assignee) { this.assignee = assignee; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public List<TaskComment> getComments() { return comments; }
    public void setComments(List<TaskComment> comments) { this.comments = comments; }

    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }

    public User getAuthor() {return author;}
    public void setAuthor(User author) {this.author=author;}

}