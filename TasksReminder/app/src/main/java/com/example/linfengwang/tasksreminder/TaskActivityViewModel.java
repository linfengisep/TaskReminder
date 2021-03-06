package com.example.linfengwang.tasksreminder;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.database.Entity.TaskItem;
import com.example.database.TaskDatabaseRepo;

import java.util.List;

import javax.inject.Inject;

public class TaskActivityViewModel extends AndroidViewModel {
    private TaskDatabaseRepo taskRepo;
    private LiveData<List<TaskItem>> mTaskLists;

   @Inject public TaskActivityViewModel(Application application){
        super(application);
        taskRepo = new TaskDatabaseRepo(application);
        mTaskLists = taskRepo.getTaskList();
    }

    public LiveData<List<TaskItem>> getAllTask(){
        return mTaskLists;
    }

    public void insertTask(TaskItem taskItem){
        taskRepo.insertTask(taskItem);
    }

    public TaskDatabaseRepo getTaskRepo() {
        return taskRepo;
    }

    public void deleteTask(TaskItem taskItem){
       taskRepo.deleteTask(taskItem);
    }
    public void updateTask(TaskItem taskItem){
       taskRepo.updateTask(taskItem);
    }
}
