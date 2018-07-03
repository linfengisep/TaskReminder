package com.example.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.database.Dao.TaskItemDao;
import com.example.database.Entity.TaskItem;
import com.example.database.converters.DateTimeConverter;
import com.example.database.converters.TaskPriorityConverter;
import com.example.database.converters.TaskStatusConverter;

import org.threeten.bp.OffsetDateTime;

@Database(version = 1,
        entities = {TaskItem.class},
        exportSchema = false
)

@TypeConverters({TaskPriorityConverter.class,
        DateTimeConverter.class,
        TaskStatusConverter.class
})

public abstract class AppDataBase extends RoomDatabase{
    public abstract TaskItemDao taskItemDao();

    private static AppDataBase INSTANCE;

    public static AppDataBase getDatabase(final Context context){
        if(INSTANCE ==null){
            synchronized (AppDataBase.class){
                if(INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,"Task_Database")
                            .addCallback(mDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //clear database every time it is created or opened;
    private static RoomDatabase.Callback mDatabaseCallback = new RoomDatabase.Callback(){
      @Override
      public void onOpen(@NonNull SupportSQLiteDatabase db){
          super.onOpen(db);
          new PopulateDbAsync(INSTANCE);
      }
    };

    //populate database
    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{
        private final TaskItemDao taskItemDao;
        PopulateDbAsync(AppDataBase dataBase){
            taskItemDao = dataBase.taskItemDao();
        }

        @Override
        protected Void doInBackground(final Void...  params){
            taskItemDao.insertTask(new TaskItem("Shopping",
                    TaskPriority.MIDDLE,
                    OffsetDateTime.now(),
                    OffsetDateTime.now(),
                    TaskItem.TaskStatus.UNDONE)
            );
            return null;
        }
    }
}
