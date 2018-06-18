package com.example.linfengwang.tasksreminder;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.database.Entity.TaskItem;
import com.example.database.TaskStatus;
import com.example.linfengwang.tasksreminder.TaskUtils.TaskPriorityConverterUtil;
import com.example.linfengwang.tasksreminder.list.TaskElement;
import com.example.linfengwang.tasksreminder.list.TaskHeaderItem;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Section;
import org.threeten.bp.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import bolts.Task;
import okhttp3.OkHttpClient;

public class TaskActivity extends AppCompatActivity {
    public final static int TASK_REQUEST_CODE=123;
    public final static int DEFAULT_VALUE=-1;
    private GroupAdapter taskItemGroup;
    private Section section;
    private RecyclerView taskItemRecyclerView;
    private TaskActivityViewModel taskViewModel;

    private List<TaskItem> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        AndroidThreeTen.init(this);
        /*init Stetho*/
        Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();


        setContentView(R.layout.activity_main);
        taskItemRecyclerView = findViewById(R.id.task_item_recycler_view);

        initRecyclerView();
        configureMenu();

        //create a viewModel;
        taskViewModel = ViewModelProviders.of(this).get(TaskActivityViewModel.class);
        taskViewModel.getAllTask()
                .observe(this, (taskItems)-> {
                            List<TaskElement> taskElementList = new ArrayList<>();
                        for(TaskItem taskItem :taskItems ){
                            taskList.add(taskItem);
                            taskElementList.add(new TaskElement(taskItem,this));
                            section.update(taskElementList);
                        }
                    }
                );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->{
            startActivityForResult(new Intent(getApplicationContext(),
                    AddTaskActivity.class),TASK_REQUEST_CODE);
                   // Toast.makeText(this,"add task",Toast.LENGTH_SHORT).show();
                    /*
                    for(TaskItem item : taskList){
                       taskViewModel.deleteTask(item);
                    }
                    */
                }
        );
    }

    private void configureMenu(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initRecyclerView(){
        taskItemGroup = new GroupAdapter();
        section = new Section();
        section.setHeader(new TaskHeaderItem(getResources().getString(R.string.task_header_text)));
        taskItemGroup.add(section);

        taskItemRecyclerView.setHasFixedSize(true);
        taskItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskItemRecyclerView.setAdapter(taskItemGroup);

        taskItemGroup.setOnItemClickListener(
                (item,view) -> Toast.makeText(this,"item clicked",Toast.LENGTH_SHORT).show()
        );
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == TASK_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                int mYear,mMonth,mDate,mHour,mMinute;
                mYear = data.getIntExtra(AddTaskActivity.TASK_DEADLINE_YEAR,DEFAULT_VALUE);
                mMonth = data.getIntExtra(AddTaskActivity.TASK_DEADLINE_MONTH,DEFAULT_VALUE);
                mDate = data.getIntExtra(AddTaskActivity.TASK_DEADLINE_DATE,DEFAULT_VALUE);
                mHour = data.getIntExtra(AddTaskActivity.TASK_DEADLINE_HOUR,DEFAULT_VALUE);
                mMinute = data.getIntExtra(AddTaskActivity.TASK_DEADLINE_MINUTE,DEFAULT_VALUE);
                if(mYear !=0 && mMonth !=0 && mDate !=0 && mHour !=0 && mMinute !=0){
                    TaskItem item = new TaskItem(
                            0,
                            data.getStringExtra(AddTaskActivity.TASK_CONTENT),
                            TaskPriorityConverterUtil.getTaskPriorityFromString(data.getStringExtra(AddTaskActivity.TASK_PRIORITY)),
                            OffsetDateTime.now(),
                            getOffsetTimeFromDate(mYear,mMonth,mDate,mHour,mMinute),
                            TaskStatus.UNDONE);
                    taskViewModel.insertTask(item);
                }
            }
        } else {
            Toast.makeText(this,"new task!",Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
