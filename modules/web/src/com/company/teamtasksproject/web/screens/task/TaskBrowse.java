package com.company.teamtasksproject.web.screens.task;

import com.haulmont.cuba.gui.screen.*;
import com.company.teamtasksproject.entity.Task;

@UiController("teamtasksproject_Task.browse")
@UiDescriptor("task-browse.xml")
@LookupComponent("tasksTable")
@LoadDataBeforeShow
public class TaskBrowse extends StandardLookup<Task> {
}