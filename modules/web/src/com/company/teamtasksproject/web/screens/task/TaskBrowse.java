package com.company.teamtasksproject.web.screens.task;

import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.teamtasksproject.entity.Task;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;

@UiController("teamtasksproject_Task.browse")
@UiDescriptor("task-browse.xml")
@LookupComponent("tasksTable")
@LoadDataBeforeShow
public class TaskBrowse extends StandardLookup<Task> {
    @Inject
    private UserSession userSession;

    @Inject
    private CollectionLoader<Task> tasksDl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        tasksDl.setParameter("assignee", userSession.getUser());
        tasksDl.load();
    }

}