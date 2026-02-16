package com.company.teamtasksproject.web.screens.project;

import com.haulmont.cuba.gui.screen.*;
import com.company.teamtasksproject.entity.Project;

@UiController("teamtasksproject_Project.browse")
@UiDescriptor("project-browse.xml")
@LookupComponent("projectsTable")
@LoadDataBeforeShow
public class ProjectBrowse extends StandardLookup<Project> {
}