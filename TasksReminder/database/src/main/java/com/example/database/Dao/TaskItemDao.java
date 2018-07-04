package com.example.database.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.database.Entity.TaskItem;

import java.util.List;

@Dao
public interface TaskItemDao {
    /** Returns a {@link } of the list of tasks. */
    @Query("SELECT * FROM TaskItem")
    LiveData<List<TaskItem>> getAll();

    /** Returns a {@link} of task. */
    @Query("SELECT * FROM TaskItem WHERE TaskItem.id =:taskId")
    LiveData<TaskItem> getTaskItemById(int taskId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTasks(List<TaskItem> tasks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(TaskItem taskItem);

    @Delete
    void deleteTasks(TaskItem taskItem);

    @Update
    void updateTask(TaskItem taskItem);
}
