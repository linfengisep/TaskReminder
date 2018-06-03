package com.example.linfengwang.tasksreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.custom_views.TaskItemView;
import com.example.database.Entity.TaskItem;
import com.example.linfengwang.tasksreminder.list.EmptyElement;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Section;

import java.util.List;

public class TaskActivity extends AppCompatActivity {
    private GroupAdapter taskItemAdapter;
    private Section section;
    private RecyclerView taskItemRecyclerView;
    private List<TaskItem> taskItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        taskItemRecyclerView = findViewById(R.id.task_item_recycler_view);

        loadTaskData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), AddTaskActivity.class)));

        taskItemAdapter = new GroupAdapter();
        section = new Section();
        //section.setPlaceholder(new EmptyElement());
        taskItemAdapter.add(section);

        taskItemRecyclerView.setHasFixedSize(true);
        taskItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskItemRecyclerView.setAdapter(taskItemAdapter);

       // section.update(taskItemList);

        taskItemAdapter.setOnItemClickListener((item,view) -> Toast.makeText(this,"item clicked",Toast.LENGTH_SHORT).show());
    }

    private void loadTaskData(){

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
