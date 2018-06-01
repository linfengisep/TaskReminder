package com.example.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.database.Dao.TaskItemDao;
import com.example.database.Entity.TaskItem;
import com.example.database.converters.DateTimeConverter;
import com.example.database.converters.TaskPriorityConverter;

@Database(version = 1,entities = {TaskItem.class}
)
@TypeConverters({TaskPriorityConverter.class,DateTimeConverter.class})
abstract class AppDataBase extends RoomDatabase{
    abstract TaskItemDao taskItemDao();
}
