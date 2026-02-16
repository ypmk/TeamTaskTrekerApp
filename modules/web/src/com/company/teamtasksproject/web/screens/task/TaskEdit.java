package com.company.teamtasksproject.web.screens.task;

import com.company.teamtasksproject.entity.Project;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.screen.*;
import com.company.teamtasksproject.entity.Task;

import javax.inject.Inject;

@UiController("teamtasksproject_Task.edit")
@UiDescriptor("task-edit.xml")
@EditedEntityContainer("taskDc")
@LoadDataBeforeShow
public class TaskEdit extends StandardEditor<Task> {
}