package com.example.linfengwang.tasksreminder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.linfengwang.tasksreminder.databinding.ActivityAddTaskBinding;

public class AddTaskActivity extends AppCompatActivity {
    private ActivityAddTaskBinding taskBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_task);

    }
}
