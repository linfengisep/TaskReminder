package com.example.linfengwang.tasksreminder;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.linfengwang.tasksreminder.TaskUtils.TaskPriorityConverteurUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.xwray.groupie.Group;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Section;

import org.threeten.bp.OffsetDateTime;

import java.util.Collection;
import java.util.List;

public class TaskActivity extends AppCompatActivity {
    public final static int TASK_REQUEST_CODE=123;
    private final static int TASK_RESULT_CODE=345;
    private GroupAdapter taskItemAdapter;
    private Section section;
    private RecyclerView taskItemRecyclerView;
    private Collection<? extends Group> taskItemList;

    private TaskActivityViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        taskItemRecyclerView = findViewById(R.id.task_item_recycler_view);

        initRecyclerView();
        configureMenu();

        //create a viewModel;
        taskViewModel = ViewModelProviders.of(this).get(TaskActivityViewModel.class);
        taskViewModel.getAllTask()
                .observe(this, new Observer<List<TaskItem>>() {
                    @Override
                    public void onChanged(@Nullable List<TaskItem> taskItems) {
                        //add new item to the adapter;
                        section.addAll(taskItemList);
                    }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                startActivityForResult(new Intent(getApplicationContext(),
                        AddTaskActivity.class),TASK_REQUEST_CODE));

    }

    private void configureMenu(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initRecyclerView(){
        taskItemAdapter = new GroupAdapter();
        section = new Section();
        //section.setPlaceholder(new EmptyElement());
        taskItemAdapter.add(section);

        taskItemRecyclerView.setHasFixedSize(true);
        taskItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskItemRecyclerView.setAdapter(taskItemAdapter);

        // section.update(taskItemList);

        taskItemAdapter.setOnItemClickListener(
                (item,view) -> Toast.makeText(this,"item clicked",Toast.LENGTH_SHORT).show()
        );
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == TASK_REQUEST_CODE && resultCode == TASK_RESULT_CODE){

            int mYear,mMonth,mDate,mHour,mMinute;
            mYear = data.getIntExtra(AddTaskActivity.TASK_DEADLINE_YEAR,0);
            mMonth = data.getIntExtra(AddTaskActivity.TASK_DEADLINE_MONTH,0);
            mDate = data.getIntExtra(AddTaskActivity.TASK_DEADLINE_DATE,0);
            mHour = data.getIntExtra(AddTaskActivity.TASK_DEADLINE_HOUR,0);
            mMinute = data.getIntExtra(AddTaskActivity.TASK_DEADLINE_MINUTE,0);

            TaskItem item = new TaskItem(
                    0,
                    data.getStringExtra(AddTaskActivity.TASK_CONTENT),
                    TaskPriorityConverteurUtil.getTaskPriorityFromString(data.getStringExtra(AddTaskActivity.TASK_PRIORITY)),
                    OffsetDateTime.now(),
                    getOffsetTimeFromDate(mYear,mMonth,mDate,mHour,mMinute),
                    TaskStatus.UNDONE);
            taskViewModel.insertTask(item);

            Toast.makeText(this,"task item",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"new task",Toast.LENGTH_SHORT).show();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
