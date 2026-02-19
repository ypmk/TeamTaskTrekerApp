package com.company.teamtasksproject.web.screens.task;

import com.company.teamtasksproject.entity.Task;
import com.company.teamtasksproject.entity.TaskStatus;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.util.List;

@UiController("teamtasksproject_TaskKanban")
@UiDescriptor("task-kanban.xml")
public class TaskKanban extends Screen {

    @Inject
    private UserSession userSession;

    @Inject
    private ScreenBuilders screenBuilders;

    @Inject
    private CollectionLoader<Task> newTasksDl;
    @Inject
    private CollectionLoader<Task> inProgressTasksDl;
    @Inject
    private CollectionLoader<Task> readyForTestTasksDl;
    @Inject
    private CollectionLoader<Task> reworkTasksDl;
    @Inject
    private CollectionLoader<Task> doneTasksDl;

    @Inject
    private DataManager dataManager;

    @Inject
    private ComponentsFactory componentsFactory;


    @Subscribe(id = "newTasksDl", target = Target.DATA_LOADER)
    private List<Task> onNewTasksDlLoad(LoadContext<Task> loadContext) {
        loadContext.getHints().put("eclipselink.query-results-cache", false);
        return dataManager.loadList(loadContext);
    }

    @Subscribe(id = "inProgressTasksDl", target = Target.DATA_LOADER)
    private List<Task> onInProgressTasksDlLoad(LoadContext<Task> loadContext) {
        loadContext.getHints().put("eclipselink.query-results-cache", false);
        return dataManager.loadList(loadContext);
    }

    @Subscribe(id = "readyForTestTasksDl", target = Target.DATA_LOADER)
    private List<Task> onReadyForTestTasksDl(LoadContext<Task> loadContext) {
        loadContext.getHints().put("eclipselink.query-results-cache", false);
        return dataManager.loadList(loadContext);
    }

    @Subscribe(id = "reworkTasksDl", target = Target.DATA_LOADER)
    private List<Task> onReworkTasksDl(LoadContext<Task> loadContext) {
        loadContext.getHints().put("eclipselink.query-results-cache", false);
        return dataManager.loadList(loadContext);
    }

    @Subscribe(id = "doneTasksDl", target = Target.DATA_LOADER)
    private List<Task> onDoneTasksDl(LoadContext<Task> loadContext) {
        loadContext.getHints().put("eclipselink.query-results-cache", false);
        return dataManager.loadList(loadContext);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        reloadBoard();
    }

    @Subscribe("refreshBtn")
    public void onRefreshBtnClick(Button.ClickEvent event) {
        reloadBoard();
    }

    private void reloadBoard() {
        User currentUser = userSession.getUser();

        newTasksDl.setParameter("assignee", currentUser);
        inProgressTasksDl.setParameter("assignee", currentUser);
        readyForTestTasksDl.setParameter("assignee", currentUser);
        reworkTasksDl.setParameter("assignee", currentUser);
        doneTasksDl.setParameter("assignee", currentUser);

        newTasksDl.setParameter("status", TaskStatus.NEW);
        inProgressTasksDl.setParameter("status", TaskStatus.IN_PROGRESS);
        readyForTestTasksDl.setParameter("status", TaskStatus.READY_FOR_TEST);
        reworkTasksDl.setParameter("status", TaskStatus.REWORK);
        doneTasksDl.setParameter("status", TaskStatus.DONE);

        newTasksDl.load();
        inProgressTasksDl.load();
        readyForTestTasksDl.load();
        reworkTasksDl.load();
        doneTasksDl.load();
    }

    @Subscribe("createBtn")
    public void onCreateBtnClick(Button.ClickEvent event) {
        Screen editor = screenBuilders.editor(Task.class, this)
                .newEntity()
                .withScreenId("teamtasksproject_Task.edit")
                .build();

        editor.addAfterCloseListener(afterCloseEvent -> reloadBoard());
        editor.show();
    }

    private void openTask(Task task) {
        if (task == null) return;

        Screen editor = screenBuilders.editor(Task.class, this)
                .editEntity(task)
                .withScreenId("teamtasksproject_Task.edit")
                .build();

        editor.addAfterCloseListener(e -> reloadBoard());
        editor.show();
    }

    private Button buildViewButton(Task task) {
        Button btn = componentsFactory.createComponent(Button.class);
        btn.setCaption("");
        btn.setIcon("font-icon:EYE");
        btn.setDescription("Просмотр");
        btn.setStyleName("icon-only");
        btn.setAction(null);
        btn.addClickListener(clickEvent -> openTask(task));
        btn.setStyleName("borderless");
        return btn;
    }

    @Install(to = "newTasksTable.view", subject = "columnGenerator")
    private Button newTasksTableViewColumnGenerator(Task task) {
        return buildViewButton(task);
    }

    @Install(to = "inProgressTasksTable.view", subject = "columnGenerator")
    private Button inProgressTasksTableViewColumnGenerator(Task task) {
        return buildViewButton(task);
    }

    @Install(to = "reviewTasksTable.view", subject = "columnGenerator")
    private Button reviewTasksTableViewColumnGenerator(Task task) {
        return buildViewButton(task);
    }

    @Install(to = "fixTasksTable.view", subject = "columnGenerator")
    private Button fixTasksTableViewColumnGenerator(Task task) {
        return buildViewButton(task);
    }

    @Install(to = "doneTasksTable.view", subject = "columnGenerator")
    private Button doneTasksTableViewColumnGenerator(Task task) {
        return buildViewButton(task);
    }


}
