package com.example.linfengwang.tasksreminder.di.module;

import android.content.Context;

import com.example.database.AppDataBase;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Provides
    public static AppDataBase applicationDatabase(Context context,String databaseName){
    }

}
