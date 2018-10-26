package com.example.linfengwang.tasksreminder.Fragment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.database.Entity.TaskItem;
import com.example.database.TaskDatabaseRepo;

import java.util.List;

import javax.inject.Inject;

public class TaskInboxFragmentViewModel extends AndroidViewModel{

    private TaskDatabaseRepo taskRepo;
    private LiveData<List<TaskItem>> inboxTaskList;

    @Inject
    public TaskInboxFragmentViewModel(Application application) {
        super(application);
        taskRepo = new TaskDatabaseRepo(application);
        inboxTaskList = taskRepo.getTaskList();
    }

    public LiveData<List<TaskItem>> getInboxTaskList() {
        return inboxTaskList;
    }

    public TaskDatabaseRepo getTaskRepo() {
        return taskRepo;
    }

    public void deleteInboxTaskItem (TaskItem item){
        taskRepo.deleteTask(item);
    }

    public void updateInboxTaskItem(TaskItem item){
        taskRepo.updateTask(item);
    }
}
