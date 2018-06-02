package com.example.linfengwang.tasksreminder;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.database.AppDataBase;
import com.example.database.Entity.TaskItem;
import com.example.database.SharePreferenceManager;
import com.example.database.TaskPriority;
import com.example.database.TaskStatus;
import com.example.database.converters.TaskPriorityConverter;
import com.example.linfengwang.tasksreminder.databinding.ActivityAddTaskBinding;

import org.threeten.bp.OffsetDateTime;

import java.util.Calendar;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {
    private ActivityAddTaskBinding taskBinding;
    private MaterialDialog datePickerDialog;
    private View positiveBtn,negativeBtn;
    private DatePicker datePicker;
    private TimePickerDialog timePicker;
    private SharePreferenceManager sharePreferenceManager;

    private int mHour, mMinute,mDate,mMonth,mYear;
    private TaskPriority priority;

    private String taskContent;

    private AppDataBase appDataBase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_task);

        sharePreferenceManager = new SharePreferenceManager(getApplicationContext());
        sharePreferenceManager.setTaskId(1);

        //get database;

        //back button;
        Toolbar toolbar = findViewById(R.id.user_profile_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            Drawable sourceImage = getResources().getDrawable(R.drawable.ic_arrow_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(sourceImage);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // task priority
        if(taskBinding.prioritySelector.getValue() !=0){
            priority = TaskPriorityConverter.fromInteger(taskBinding.prioritySelector.getValue());
        }

        // task content;
        taskBinding.editTaskContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                taskContent = s.toString().trim();
                Toast.makeText(getApplicationContext(),taskContent,Toast.LENGTH_SHORT).show();
            }
        });
        //task creation time
        //OffsetDateTime rightNow = OffsetDateTime.now();
        //submit task;
        taskBinding.submitButton.setOnClickListener(view ->{
            //show words to users;
            if(taskContent.isEmpty()){
                Toast.makeText(this,"Add a task",Toast.LENGTH_SHORT
                ).show();
            }else {
                Toast.makeText(this,"Task:"+taskContent+","+priority+"priority is saved",Toast.LENGTH_SHORT
                ).show();
            }

            /*TaskItem taskItem = new TaskItem(taskContent,
                    priority,
                    rightNow,
                    getOffsetTimeFromDate(mYear,mMonth,mDate,mHour,mMinute),
                    TaskStatus.UNDONE
            );
            */
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                    Intent liveActivity = new Intent(AddTaskActivity.this, MainActivity.class);
                    startActivity(liveActivity);
                    finish();
                break;
            default:
                break;
        }
        return true;
    }

    public void openDatePickerDialog(View view){
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
        positiveBtn = datePickerDialog.getActionButton(DialogAction.POSITIVE);
        negativeBtn = datePickerDialog.getActionButton(DialogAction.NEGATIVE);

        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datePickerDialog.getCustomView() !=null){
                    datePicker =datePickerDialog.getCustomView().findViewById(R.id.dialog_date_picker);
                    if(datePicker !=null){
                        mDate= datePicker.getDayOfMonth();
                        mMonth = datePicker.getMonth();
                        mYear = datePicker.getYear();

                    }
                }
                datePickerDialog.dismiss();
                taskBinding.datePicker.setText(String.format(Locale.getDefault(),"%d/%d/%d",mDate,mMonth,mYear));
            }
        });

        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.dismiss();
            }
        });

    }

    public void openTimePickerDialog(View view){
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        timePicker = new TimePickerDialog(AddTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                mHour = selectedHour;
                mMinute = selectedMinute;
                taskBinding.timePicker.setText(String.format(Locale.getDefault(),"%d : %d",mHour,mMinute));
            }
        }, hour, minute, true);
        timePicker.setTitle("Select Time");
        timePicker.show();
    }

    private OffsetDateTime getOffsetTimeFromDate(int mYear,int mMonth,int mDate,int mHour,int mMinute){
        OffsetDateTime deadline = OffsetDateTime.now();
        deadline.withYear(mYear);
        deadline.withMonth(mMonth);
        deadline.withDayOfMonth(mDate);
        deadline.withHour(mHour);
        deadline.withMinute(mMinute);
        return deadline;
    }
}
