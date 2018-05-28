package com.example.linfengwang.tasksreminder;
import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.custom_views.TaskItemView;
import com.example.linfengwang.tasksreminder.TaskUtils.CircleTransformation;
import com.example.linfengwang.tasksreminder.TaskUtils.DpUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TaskItemTemp> dataList;
    private Context context;
    private TaskItemClickListener itemClickListener;
    private PreferenceManager sharedPreferenceManager;

    public TaskAdapter(Context context, List<TaskItemTemp> list,
                       TaskItemClickListener listener, PreferenceManager sharedPreferenceManager) {
        this.dataList = list;
        this.context = context;
        this.itemClickListener = listener;
        this.sharedPreferenceManager = sharedPreferenceManager;
    }

    @NonNull @Override public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskHolder(new TaskItemView(context));
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TaskHolder) holder).setContent(dataList.get(position));
    }

    @Override public int getItemCount() {
        return dataList.size();
    }

    public interface TaskItemClickListener {
        void onCardItemClick(TaskItemTemp taskItem);
    }

    // friend info view holder;
    public class TaskHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView icon;
        private AppCompatTextView taskTitle;
        private AppCompatTextView taskSubTitle;

        TaskHolder(View v) {
            super(v);
            icon = v.findViewById(R.id.task_item_icon);
            taskTitle = v.findViewById(R.id.task_item_title);
            taskSubTitle = v.findViewById(R.id.task_item_subtitle);

            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Toast.makeText(context,"click",Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void setContent(TaskItemTemp taskItem){
            taskTitle.setText(taskItem.task);
            taskSubTitle.setText(taskItem.taskPriority);
            drawRoundedImage(icon,R.drawable.panda, DpUtil.dpToPixel(40,context));
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
