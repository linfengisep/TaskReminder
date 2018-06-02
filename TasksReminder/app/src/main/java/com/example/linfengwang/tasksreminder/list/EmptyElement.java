package com.example.linfengwang.tasksreminder.list;

import android.support.annotation.NonNull;
import android.view.View;

import com.example.linfengwang.tasksreminder.R;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class EmptyElement extends Item<EmptyElement.EmptyElementViewHolder> {
    @Override
    public void bind(@NonNull EmptyElementViewHolder viewHolder, int position) {

    }

    @Override
    public int getLayout() {
        return R.layout.empty_task_element_layout;
    }

    class EmptyElementViewHolder extends ViewHolder {
        EmptyElementViewHolder(View rootView){
            super(rootView);
        }
    }
}
