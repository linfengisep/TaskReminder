package com.example.linfengwang.tasksreminder.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.linfengwang.tasksreminder.R;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class EmptyElement extends Item<EmptyElement.EmptyElementViewHolder> {
    String title;

    public EmptyElement(String title) {
        this.title = title;
    }

    @Override
    public void bind(@NonNull EmptyElementViewHolder viewHolder, int position) {
        viewHolder.setContent(title);
    }

    @Override
    public int getLayout() {
        return R.layout.empty_task_element_layout;
    }

    class EmptyElementViewHolder extends ViewHolder {
        AppCompatTextView titleView;
        EmptyElementViewHolder(View rootView){
            super(rootView);
            titleView = rootView.findViewById(R.id.empty_item_title);
        }

        public void setContent(String title){
            titleView.setText(title);
        }
    }
}
