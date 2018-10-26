package com.example.linfengwang.tasksreminder.di.component;

import com.example.linfengwang.tasksreminder.di.TaskApp;
import com.example.linfengwang.tasksreminder.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
@Singleton   //make sure to generate one instance
@Component(modules = AppModule.class)
public interface AppComponent {
    DbComponent dbComponent();

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(TaskApp taskApp);
        @BindsInstance Builder databaseName(String databaseName);
        
        AppComponent build();
    }
}
