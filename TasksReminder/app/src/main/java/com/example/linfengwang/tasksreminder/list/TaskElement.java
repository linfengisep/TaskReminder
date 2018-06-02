package com.example.linfengwang.tasksreminder.list;

import android.support.annotation.NonNull;
import android.view.View;

import com.example.linfengwang.tasksreminder.R;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class TaskElement extends Item<TaskElement.TaskElementViewHolder> {

    @Override
    public void bind(@NonNull TaskElementViewHolder viewHolder, int position) {

    }

    @Override
    public int getLayout() {
        return R.layout.task_element_layout;
    }

    class TaskElementViewHolder extends ViewHolder{
        TaskElementViewHolder(View rootView){
            super(rootView);
        }
    }
}
