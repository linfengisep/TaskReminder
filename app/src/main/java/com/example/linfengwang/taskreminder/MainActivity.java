package com.example.linfengwang.taskreminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button addItemButton;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemButton = findViewById(R.id.add_item_button);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "add item", Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView = findViewById(R.id.task_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Task> taskList;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public UserCardView userCardView;

            public ViewHolder(TextView v) {
                super(v);
                userCardView = (UserCardView)v;
            }
        }



        public MyAdapter(List<Task> mDataset) {
            taskList = mDataset;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            return new ViewHolder(userCardView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setTitle(taskList.get(position).taskContent);
            holder.setSubtitle(String.format(Locale.getDefault(),"%d",taskList.get(position).taskPriority));
        }

        @Override
        public int getItemCount() {
            return taskList.size();
        }
    }
}
