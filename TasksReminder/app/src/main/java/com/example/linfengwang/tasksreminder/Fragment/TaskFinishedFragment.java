package com.example.linfengwang.tasksreminder.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.custom_views.TaskItemView;
import com.example.database.Entity.TaskItem;
import com.example.database.TaskPriority;
import com.example.database.converters.TaskPriorityConverter;
import com.example.linfengwang.tasksreminder.R;
import com.example.linfengwang.tasksreminder.TaskUtils.CircleTransformation;
import com.example.linfengwang.tasksreminder.TaskUtils.DpUtil;
import com.squareup.picasso.Picasso;

import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskFinishedFragment extends Fragment {
    private RecyclerView finishedTaskRecyclerView;
    private List<TaskItem> taskList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.task_finished_layout,container,false);
        finishedTaskRecyclerView = view.findViewById(R.id.finished_task_section);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        finishedTaskRecyclerView.setHasFixedSize(true);
        finishedTaskRecyclerView.setLayoutManager(layoutManager);

        loadingData();
        passDataToAdapter(this.taskList);

        return view;
    }

    private void loadingData(){
        //test section task for after tomorrow;
        TaskItem test1 = new TaskItem("test1",
                TaskPriority.HIGH,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                TaskItem.TaskStatus.UNDONE);
        taskList.add(test1);
        TaskItem test2 = new TaskItem("test2",
                TaskPriority.HIGH,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                TaskItem.TaskStatus.UNDONE);
        taskList.add(test2);
        TaskItem test3 = new TaskItem("test3",
                TaskPriority.HIGH,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                TaskItem.TaskStatus.UNDONE);
        taskList.add(test3);
    }

    private void passDataToAdapter(List<TaskItem> taskList){
        TaskFinishAdapter adapter = new TaskFinishAdapter(taskList);
        finishedTaskRecyclerView.setAdapter(adapter);
    }

    public class TaskFinishAdapter extends RecyclerView.Adapter<TaskFinishAdapter.FinishedTaskViewHolder>{
        List<TaskItem> taskList;

        TaskFinishAdapter(List<TaskItem> taskList){
            this.taskList =taskList;
        }

        @NonNull
        @Override
        public FinishedTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FinishedTaskViewHolder(new TaskItemView(getContext()));
        }

        @Override
        public void onBindViewHolder(@NonNull FinishedTaskViewHolder holder, int position) {
            holder.setContent(taskList.get(position));
        }

        @Override
        public int getItemCount() {
            return taskList.size();
        }

        class FinishedTaskViewHolder extends RecyclerView.ViewHolder{
            private AppCompatImageView icon;
            private AppCompatTextView taskTitle;
            private AppCompatTextView taskSubTitle;

            FinishedTaskViewHolder(View v) {
                super(v);
                icon = v.findViewById(R.id.task_item_icon);
                taskTitle = v.findViewById(R.id.task_item_title);
                taskSubTitle = v.findViewById(R.id.task_item_subtitle);
                //view take full screen;
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT);
                v.setLayoutParams(params);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        Toast.makeText(getContext(),"click",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void setContent(TaskItem taskItem){
                taskTitle.setText(taskItem.getTaskContent());
                taskSubTitle.setText(String.format(Locale.getDefault(),"%d", TaskPriorityConverter.toInteger(taskItem.getTaskPriority())));
                drawRoundedImage(icon,R.drawable.panda, DpUtil.dpToPixel(40,getContext()));
            }
            private void drawRoundedImage(AppCompatImageView iconView,int imageRes, int imageSize) {
                Picasso.get()
                        .load(imageRes)
                        .error(R.drawable.ic_person)
                        .placeholder(R.drawable.ic_person)
                        .resize(imageSize, imageSize)
                        .transform(new CircleTransformation())
                        .into(iconView);
            }
        }
    }
}
