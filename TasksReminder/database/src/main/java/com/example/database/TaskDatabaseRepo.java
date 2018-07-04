package com.example.database;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.database.Dao.TaskItemDao;
import com.example.database.Entity.TaskItem;

import java.util.List;

/*
 *this class manage multiply thread in the background to save the task item to database;
 */
public class TaskDatabaseRepo {
    private TaskItemDao taskItemDao;
    private LiveData<List<TaskItem>> taskList;

    public TaskDatabaseRepo(Application application){
        AppDataBase db = AppDataBase.getDatabase(application);
        taskItemDao = db.taskItemDao();
        taskList = taskItemDao.getAll();
    }

    public LiveData<List<TaskItem>> getTaskList() {
        return taskList;
    }

    public void insertTask(TaskItem item){
        new InsertTaskAsyncTask(taskItemDao).execute(item);
    }

    private static class InsertTaskAsyncTask extends AsyncTask<TaskItem,Void,Void> {
        private TaskItemDao taskItemDao;
        InsertTaskAsyncTask(TaskItemDao mTaskItemDao){
            taskItemDao = mTaskItemDao;
        }

        @Override protected Void doInBackground(final TaskItem ...params){
            taskItemDao.insertTask(params[0]);
            return null;
        }
    }

    public void deleteTask(TaskItem item){
        new DeleteTaskAsyncTask(taskItemDao).execute(item);
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<TaskItem,Void,Void>{
        private TaskItemDao taskItemDao;
        DeleteTaskAsyncTask(TaskItemDao taskItemDao){this.taskItemDao = taskItemDao;}
        @Override
        protected Void doInBackground(final TaskItem... taskItems) {
            taskItemDao.deleteTasks(taskItems[0]);
            return null;
        }
    }

    public void updateTask(TaskItem taskItem){new UpdateTaskItemAsyncTask(taskItemDao).execute(taskItem);}

    private static class UpdateTaskItemAsyncTask extends AsyncTask<TaskItem, Void, Void> {
        private TaskItemDao taskItemDao;
        UpdateTaskItemAsyncTask(TaskItemDao taskItemDao){this.taskItemDao = taskItemDao;}

        @Override
        protected Void doInBackground(TaskItem... taskItems) {
            taskItemDao.updateTask(taskItems[0]);
            return null;
        }
    }
}
