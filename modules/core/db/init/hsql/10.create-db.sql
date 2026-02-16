-- begin TEAMTASKSPROJECT_PROJECT
create table TEAMTASKSPROJECT_PROJECT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end TEAMTASKSPROJECT_PROJECT
-- begin TEAMTASKSPROJECT_TASK_COMMENT
create table TEAMTASKSPROJECT_TASK_COMMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TASK_ID varchar(36) not null,
    AUTHOR_ID varchar(36) not null,
    TEXT longvarchar not null,
    --
    primary key (ID)
)^
-- end TEAMTASKSPROJECT_TASK_COMMENT
-- begin TEAMTASKSPROJECT_TAG
create table TEAMTASKSPROJECT_TAG (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end TEAMTASKSPROJECT_TAG
-- begin TEAMTASKSPROJECT_TASK
create table TEAMTASKSPROJECT_TASK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TITLE varchar(255) not null,
    DESCRIPTION longvarchar,
    PROJECT_ID varchar(36) not null,
    STATUS varchar(50) not null,
    PRIORITY varchar(50) not null,
    ASSIGNEE_ID varchar(36),
    DUE_DATE date,
    --
    primary key (ID)
)^
-- end TEAMTASKSPROJECT_TASK
-- begin TEAMTASKSPROJECT_TASK_TEAMTASKSPROJECT_TAG_LINK
create table TEAMTASKSPROJECT_TASK_TEAMTASKSPROJECT_TAG_LINK (
    TASK_ID varchar(36) not null,
    TAG_ID varchar(36) not null,
    primary key (TASK_ID, TAG_ID)
)^
-- end TEAMTASKSPROJECT_TASK_TEAMTASKSPROJECT_TAG_LINK
