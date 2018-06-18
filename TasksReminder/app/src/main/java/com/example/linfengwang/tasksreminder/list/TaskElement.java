package com.example.linfengwang.tasksreminder.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.database.Entity.TaskItem;
import com.example.database.TaskPriority;
import com.example.database.converters.TaskPriorityConverter;
import com.example.linfengwang.tasksreminder.R;
import com.example.linfengwang.tasksreminder.TaskUtils.TimeFormat;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;
import java.util.Locale;

public class TaskElement extends Item<TaskElement.TaskElementViewHolder> {
    private TaskItem taskItem;
    private Context context;
    public TaskElement(TaskItem taskItem,Context context){
        this.taskItem = taskItem;
        this.context = context;
    }

    @Override @NonNull
    public TaskElementViewHolder createViewHolder(@Nullable View itemView){
        return new TaskElement.TaskElementViewHolder(itemView);
    }

    @Override
    public void bind(@NonNull TaskElementViewHolder viewHolder, int position) {
        viewHolder.setTaskTitle(taskItem.getTaskContent());
        viewHolder.setTaskSubtitle(TimeFormat.formatTime(context,
                taskItem.getTaskDeadline().toEpochSecond()*1000));
        viewHolder.setTaskPriority(String.format(Locale.getDefault(),"%s",
                priorityConverter(viewHolder,
                        TaskPriorityConverter.toInteger(taskItem.getTaskPriority()
                ))
        ));
        viewHolder.setTaskIcon(taskItem.getTaskContent().substring(0,1));
    }

    @Override
    public int getLayout() {
        return R.layout.task_element_layout;
    }

    private String priorityConverter(TaskElementViewHolder holder,int level){
        switch (level){
            case 1:
                holder.setTaskIconColor(R.color.colorAccent);
                return context.getString(R.string.low);
            case 2:
                holder.setTaskIconColor(R.color.colorPrimary);
                return context.getString(R.string.middle);
            case 3:
                holder.setTaskIconColor(R.color.colorPrimaryDark);
                return context.getString(R.string.high);

            default:
                holder.setTaskIconColor(R.color.colorAccent);
                return context.getString(R.string.low);
        }
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
        private void setTaskIconColor(int color){
            taskIcon.setTextColor(color);
        }
    }
}
