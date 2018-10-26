package com.example.linfengwang.tasksreminder.Fragment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.database.Entity.TaskItem;
import com.example.database.TaskDatabaseRepo;

import java.util.List;

import javax.inject.Inject;
//extends AndroidViewModel instead of extends ViewModel, cus its include a Application references.
public class TaskFinishedFragmentViewModel extends AndroidViewModel {
    private TaskDatabaseRepo taskDatabaseRepo;
    private LiveData<List<TaskItem>> taskFinishedList;

    @Inject
    public TaskFinishedFragmentViewModel(Application application) {
        super(application);
        taskDatabaseRepo = new TaskDatabaseRepo(application);
        taskFinishedList = taskDatabaseRepo.getTaskList();
    }

    public LiveData<List<TaskItem>> getTaskFinishedList() {
        return taskFinishedList;
    }

    public void deleteFinishedTaskItem(TaskItem item){
        taskDatabaseRepo.deleteTask(item);
    }

    public void updateFinishedTaskItem(TaskItem item){
        taskDatabaseRepo.updateTask(item);
    }
}
