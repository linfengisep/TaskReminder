package com.example.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.database.Dao.TaskItemDao;
import com.example.database.Entity.TaskItem;
import com.example.database.converters.DateTimeConverter;
import com.example.database.converters.TaskPriorityConverter;
import com.example.database.converters.TaskStatusConverter;

@Database(version = 1,
        entities = {TaskItem.class},
        exportSchema = true
)

@TypeConverters({TaskPriorityConverter.class,
        DateTimeConverter.class,
        TaskStatusConverter.class
})

public abstract class AppDataBase extends RoomDatabase{
    abstract TaskItemDao taskItemDao();


    private static AppDataBase INSTANCE;

    static AppDataBase getDatabase(final Context context){
        if(INSTANCE !=null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,"Task_Database").build();
        }
        return INSTANCE;
    }
}
