package com.example.linfengwang.tasksreminder.di;

import android.app.Application;

import com.example.linfengwang.tasksreminder.di.component.AppComponent;
import com.example.linfengwang.tasksreminder.di.component.DbComponent;
import com.jakewharton.threetenabp.AndroidThreeTen;

public class TaskApp extends Application{
    private AppComponent appComponent;
    private DbComponent dbComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        appComponent = DaggerAppComponent.build();
    }

    public DbComponent getDbComponent() {
        return dbComponent;
    }
}
