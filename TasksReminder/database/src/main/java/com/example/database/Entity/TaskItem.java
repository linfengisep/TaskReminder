package com.example.database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.database.TaskPriority;

import org.threeten.bp.OffsetDateTime;


@Entity(indices = {
        @Index(value = {"taskContent"}),
        @Index(value = {"id"})
}) public class TaskItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String taskContent;
    private TaskPriority taskPriority;
    private OffsetDateTime taskDeadline;
    private OffsetDateTime taskCreationDate;
    private TaskStatus taskStatus;

    public TaskItem(String taskContent,
                    TaskPriority taskPriority,
                    OffsetDateTime taskCreationDate,
                    OffsetDateTime taskDeadline,
                    TaskStatus taskStatus) {
        this(0,taskContent,taskPriority,taskCreationDate,taskDeadline,taskStatus);
    }

    public TaskItem(int taskId, String taskContent,
                    TaskPriority taskPriority,
                    OffsetDateTime taskCreationDate,
                    OffsetDateTime taskDeadline,
                    TaskStatus taskStatus) {
        this.id = taskId;
        this.taskContent = taskContent;
        this.taskPriority = taskPriority;
        this.taskCreationDate = taskCreationDate;
        this.taskDeadline = taskDeadline;
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

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public OffsetDateTime getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(OffsetDateTime taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public OffsetDateTime getTaskCreationDate() {
        return taskCreationDate;
    }

    public void setTaskCreationDate(OffsetDateTime taskCreationDate) {
        this.taskCreationDate = taskCreationDate;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskItem taskItem = (TaskItem) o;

        if (id != taskItem.id) return false;
        if (!taskContent.equals(taskItem.taskContent)) return false;
        if (taskPriority != taskItem.taskPriority) return false;
        if (!taskDeadline.equals(taskItem.taskDeadline)) return false;
        if (!taskCreationDate.equals(taskItem.taskCreationDate)) return false;
        return taskStatus == taskItem.taskStatus;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + taskContent.hashCode();
        result = 31 * result + taskPriority.hashCode();
        result = 31 * result + taskDeadline.hashCode();
        result = 31 * result + taskCreationDate.hashCode();
        result = 31 * result + taskStatus.hashCode();
        return result;
    }

    public enum TaskStatus {
        UNDONE,
        DONE,
        STANDBY
    }
}
