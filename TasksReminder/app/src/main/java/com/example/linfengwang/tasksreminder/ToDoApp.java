package com.example.linfengwang.tasksreminder;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
@Module
public class ToDoApp extends Application {
    private static ToDoApp mInstance;
   @Provides public static synchronized ToDoApp getInstance(){
        return mInstance;
    }
    public void onCreate(){
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        mInstance = this;
    }
}
