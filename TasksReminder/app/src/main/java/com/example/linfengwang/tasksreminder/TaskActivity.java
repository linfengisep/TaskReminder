package com.example.linfengwang.tasksreminder;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.database.Entity.TaskItem;
import com.example.linfengwang.tasksreminder.TaskUtils.TaskPriorityConverterUtil;
import com.example.linfengwang.tasksreminder.TaskUtils.TimeFormat;
import com.example.linfengwang.tasksreminder.list.EmptyElement;
import com.example.linfengwang.tasksreminder.list.TaskElement;
import com.example.linfengwang.tasksreminder.list.TaskHeaderItem;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Section;

import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;
import okhttp3.OkHttpClient;

public class TaskActivity extends AppCompatActivity {
    public final static int TASK_REQUEST_CODE=123;
    public final static int DEFAULT_VALUE=-1;
    private GroupAdapter taskItemGroup;
    private Section sectionTaskToday;
    private Section sectionTaskAfter;
    private RecyclerView taskItemRecyclerView;
    private TaskActivityViewModel taskViewModel;
    private ArrayMap<Integer,TaskItem> taskItemArrayMap = new ArrayMap<>();

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
                        for(TaskItem taskItem :taskItems ){
                            long diff = TimeFormat.compareDifference(taskItem.getTaskDeadline().toEpochSecond()*1000,
                                    System.currentTimeMillis());
                            if(diff==0){
                                if(!taskItemArrayMap.containsKey(taskItem.getId())){
                                    sectionTaskToday.add(new TaskElement(taskItem,this));
                                    taskItemArrayMap.put(taskItem.getId(),taskItem);
                                }
                            } else if(TimeFormat.compareDifference(taskItem.getTaskDeadline().toEpochSecond()*1000,
                                    System.currentTimeMillis())>0){
                                if(!taskItemArrayMap.containsKey(taskItem.getId())){
                                    sectionTaskAfter.add(new TaskElement(taskItem,this));
                                    taskItemArrayMap.put(taskItem.getId(),taskItem);
                                }
                            } else {
                                //add those task to the inbox;
                            }
                        }
                    }
                );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->{
            startActivityForResult(new Intent(getApplicationContext(),
                    AddTaskActivity.class),TASK_REQUEST_CODE);
                }
        );
    }

    private void configureMenu(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            //set image
            Drawable sourceImage = getResources().getDrawable(R.drawable.ic_resume);
            getSupportActionBar().setHomeAsUpIndicator(sourceImage);
        }

    }

    private void initRecyclerView(){
        sectionTaskToday = new Section();
        sectionTaskAfter = new Section();
        sectionTaskToday.setHeader(new TaskHeaderItem(getResources().getString(R.string.today_task_header)));
        sectionTaskAfter.setHeader(new TaskHeaderItem(getResources().getString(R.string.after_today_task_header)));
        sectionTaskToday.setHideWhenEmpty(true);
        sectionTaskAfter.setHideWhenEmpty(true);
        //show this section when empty;
        sectionTaskToday.setPlaceholder(new EmptyElement(getResources().getString(R.string.today_no_task_reminder)));
        sectionTaskAfter.setPlaceholder(new EmptyElement(getResources().getString(R.string.tomorrow_no_task_reminder)));

        taskItemGroup = new GroupAdapter();
        taskItemGroup.add(sectionTaskToday);
        taskItemGroup.add(sectionTaskAfter);

        taskItemRecyclerView.setHasFixedSize(true);
        taskItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskItemRecyclerView.setAdapter(taskItemGroup);

        taskItemGroup.setOnItemClickListener(
                (item,view) -> {
                    Toast.makeText(this,"Clicked:"+item.toString(),Toast.LENGTH_SHORT).show();
                    view.setBackgroundColor(ContextCompat.getColor(view.getContext(),R.color.light_blue));
                }
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
                            TaskPriorityConverterUtil.getTaskPriorityFromString(data.getIntExtra(AddTaskActivity.TASK_PRIORITY,0)),
                            OffsetDateTime.now(),
                            getOffsetTimeFromDate(mYear,mMonth,mDate,mHour,mMinute),
                            TaskItem.TaskStatus.UNDONE);
                    taskViewModel.insertTask(item);
                }
            }
        } else {
            Toast.makeText(this,"new task!",Toast.LENGTH_SHORT).show();
        }
    }

    private OffsetDateTime getOffsetTimeFromDate(int mYear,int mMonth,int mDate,int mHour,int mMinute){
         return OffsetDateTime.of(mYear,mMonth,mDate,mHour,mMinute,0,0,
                 ZoneId.systemDefault().getRules().getOffset(Instant.now()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this,TaskReviewActivity.class));
                finish();
                break;
            case R.id.action_settings:
                Toast.makeText(this,"en cours",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
