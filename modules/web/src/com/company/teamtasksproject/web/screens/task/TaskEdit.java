package com.company.teamtasksproject.web.screens.task;

import com.company.teamtasksproject.entity.TaskStatus;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.screen.*;
import com.company.teamtasksproject.entity.Task;

import javax.inject.Inject;

@UiController("teamtasksproject_Task.edit")
@UiDescriptor("task-edit.xml")
@EditedEntityContainer("taskDc")
@LoadDataBeforeShow
public class TaskEdit extends StandardEditor<Task> {
    @Inject
    private Notifications notifications;

    @Subscribe("statusField")
    public void onStatusFieldValueChange(HasValue.ValueChangeEvent<TaskStatus> event) {
        TaskStatus oldStatus = event.getPrevValue();
        TaskStatus newStatus = event.getValue();

        if (oldStatus == null || newStatus == null) return;

        boolean allowed =
                (oldStatus == TaskStatus.NEW &&
                        (newStatus == TaskStatus.IN_PROGRESS || newStatus == TaskStatus.CANCELED)) ||
                        (oldStatus == TaskStatus.IN_PROGRESS && (newStatus == TaskStatus.DONE || newStatus == TaskStatus.CANCELED)) ||
                        (oldStatus == TaskStatus.CANCELED && newStatus == TaskStatus.CANCELED) ||
                        (oldStatus == newStatus);

        if (!allowed) {
            getEditedEntity().setStatus(oldStatus);
            notifications.create()
                    .withCaption("Нельзя перевести задачу из статуса " + oldStatus + " в " + newStatus)
                    .show();
        }
    }

    @Inject
    private UserSessionSource userSessionSource;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Task> event) {
        event.getEntity().setStatus(TaskStatus.NEW);
        event.getEntity().setAuthor(userSessionSource.getUserSession().getUser());
    }

}