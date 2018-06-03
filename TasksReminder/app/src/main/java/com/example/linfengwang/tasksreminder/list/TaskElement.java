package com.example.linfengwang.tasksreminder.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.database.Entity.TaskItem;
import com.example.database.converters.TaskPriorityConverter;
import com.example.linfengwang.tasksreminder.R;
import com.example.linfengwang.tasksreminder.TaskUtils.TimeFormat;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.List;
import java.util.Locale;

public class TaskElement extends Item<TaskElement.TaskElementViewHolder> {
    private List<TaskItem> taskItemList;
    private Context context;
    public TaskElement(List<TaskItem> taskItemList,Context context){
        this.taskItemList = taskItemList;
        this.context = context;
    }

    @Override @NonNull
    public TaskElementViewHolder createViewHolder(@Nullable View itemView){
        return new TaskElement.TaskElementViewHolder(itemView);
    }

    @Override
    public void bind(@NonNull TaskElementViewHolder viewHolder, int position) {
        viewHolder.setTaskTitle(taskItemList.get(position).getTaskContent());
        viewHolder.setTaskSubtitle(TimeFormat.formatTime(context,
                taskItemList.get(position).getTaskDeadline().toEpochSecond()*1000));
        viewHolder.setTaskPriority(String.format(Locale.getDefault(),"%d",
                TaskPriorityConverter.toInteger(taskItemList.get(position).getTaskPriority())));
        viewHolder.setTaskIcon(taskItemList.get(position).getTaskContent().substring(0,1));

    }

    @Override
    public int getLayout() {
        return R.layout.task_element_layout;
    }

    class TaskElementViewHolder extends ViewHolder{
         AppCompatTextView taskIcon,taskTitle,taskSubtitle,taskPriority;
        TaskElementViewHolder(View rootView){
            super(rootView);
            taskIcon = rootView.findViewById(R.id.task_item_icon);
            taskTitle = rootView.findViewById(R.id.task_item_title);
            taskSubtitle = rootView.findViewById(R.id.task_item_subtitle);
            taskPriority = rootView.findViewById(R.id.task_item_priority);
        }

        private void setTaskIcon(String taskLetter){
            taskIcon.setText(taskLetter);
        }
        private void setTaskTitle(String taskContent){
            taskTitle.setText(taskContent);
        }
        private void setTaskSubtitle(String deadline){
            taskSubtitle.setText(deadline);
        }
        private void setTaskPriority(String priority){
            taskPriority.setText(priority);
        }
    }
}
