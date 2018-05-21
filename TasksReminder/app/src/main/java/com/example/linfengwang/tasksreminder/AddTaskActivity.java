package com.example.linfengwang.tasksreminder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.linfengwang.tasksreminder.databinding.ActivityAddTaskBinding;

public class AddTaskActivity extends AppCompatActivity {
    private ActivityAddTaskBinding taskBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_task);
        taskBinding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"value:"+taskBinding.prioritySelector.getValue(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void openDatePickerDialog(View view){
        Toast.makeText(getApplicationContext(),"date picker",Toast.LENGTH_SHORT).show();

        new MaterialDialog.Builder(getApplicationContext())
                .title("time picker")
                .customView(R.layout.date_picker_dialog,false)
                .positiveText("valid")
                .negativeText("cancel")
                .show();
    }
}
