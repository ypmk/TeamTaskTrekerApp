package com.company.teamtasksproject.web.screens.project;

import com.haulmont.cuba.gui.screen.*;
import com.company.teamtasksproject.entity.Project;

@UiController("teamtasksproject_Project.edit")
@UiDescriptor("project-edit.xml")
@EditedEntityContainer("projectDc")
@LoadDataBeforeShow
public class ProjectEdit extends StandardEditor<Project> {
}