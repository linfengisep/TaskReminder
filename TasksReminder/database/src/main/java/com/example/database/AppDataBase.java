package com.example.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.database.Dao.TaskItemDao;
import com.example.database.Entity.TaskItem;

@Database(version = 1,entities = {TaskItem.class})
abstract class AppDataBase extends RoomDatabase{
    abstract TaskItemDao taskItemDao();
}
