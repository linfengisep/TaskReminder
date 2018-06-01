package com.example.database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.database.TaskPriority;
import com.example.database.TaskStatus;

import java.time.OffsetDateTime;


@Entity(indices = {
        @Index(value = {"taskContent"}),
        @Index(value = {"id"})
}) public class TaskItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String taskContent;
    private TaskPriority taskPriority;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastUpdate;
    private TaskStatus taskStatus;

    public TaskItem(String taskContent, TaskPriority taskPriority, OffsetDateTime creationDate, OffsetDateTime lastUpdate, TaskStatus taskStatus) {
        this(0,taskContent,taskPriority,creationDate,lastUpdate,taskStatus);
    }

    public TaskItem(int taskId, String taskContent, TaskPriority taskPriority, OffsetDateTime creationDate, OffsetDateTime lastUpdate, TaskStatus taskStatus) {
        this.id = taskId;
        this.taskContent = taskContent;
        this.taskPriority = taskPriority;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.taskStatus = taskStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public OffsetDateTime getLastUpdate() {
        return lastUpdate;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }
}