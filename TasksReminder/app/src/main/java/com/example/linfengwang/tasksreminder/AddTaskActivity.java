package com.example.linfengwang.tasksreminder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.linfengwang.tasksreminder.databinding.ActivityAddTaskBinding;

public class AddTaskActivity extends AppCompatActivity {
    private ActivityAddTaskBinding taskBinding;
    private MaterialDialog datePickerDialog;
    private View positiveBtn,negativeBtn;
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

        datePickerDialog=new MaterialDialog.Builder(view.getContext())
                .title(getResources().getString(R.string.time_picker))
                .customView(R.layout.date_picker_dialog,false)
                .positiveText(getResources().getString(R.string.valid))
                .negativeText(getResources().getString(R.string.cancel))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(getApplicationContext(),""+dialog.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

        DatePicker datePicker =(DatePicker)datePickerDialog.getCustomView();
        if(datePicker !=null){
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();
            Log.d("add item","day:"+day+"month:"+month+"year:"+year);
        }
        positiveBtn = datePickerDialog.getActionButton(DialogAction.POSITIVE);
        negativeBtn = datePickerDialog.getActionButton(DialogAction.NEGATIVE);

        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"valid",Toast.LENGTH_SHORT).show();
                datePickerDialog.dismiss();
            }
        });

        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_SHORT).show();
                datePickerDialog.dismiss();
            }
        });

    }


}
