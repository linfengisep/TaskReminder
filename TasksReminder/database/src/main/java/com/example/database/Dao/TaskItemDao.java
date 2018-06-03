package com.example.database.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.database.Entity.TaskItem;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface TaskItemDao {
    /** Returns a {@link } of the list of tasks. */
    @Query("SELECT * FROM TaskItem ORDER by creationDate ASC")
    LiveData<List<TaskItem>> getAll();

    /** Returns a {@link Flowable} of task. */
    @Query("SELECT * FROM TaskItem WHERE TaskItem.id =:taskId ORDER by creationDate ASC")
    LiveData<List<TaskItem>> getUserFriends(int taskId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTasks(List<TaskItem> tasks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(TaskItem taskItem);
    @Delete
    void deleteTasks(List<TaskItem> taskItems);

}
