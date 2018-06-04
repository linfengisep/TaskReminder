package com.example.linfengwang.tasksreminder.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.linfengwang.tasksreminder.R;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class TaskHeaderItem extends Item<TaskHeaderItem.HeaderViewHolder> {

    private final String title;

    public TaskHeaderItem(final String title) {
        super();
        this.title = title;
    }

    @NonNull
    @Override public HeaderViewHolder createViewHolder(@NonNull View itemView) {
        return new HeaderViewHolder(itemView);
    }

    @Override public void bind(@NonNull HeaderViewHolder viewHolder, int position) {
        viewHolder.setHeader(title);
    }

    @Override public int getLayout() {
        return R.layout.header_item;
    }

    static class HeaderViewHolder extends ViewHolder {

        private final AppCompatTextView headerTextView;

        HeaderViewHolder(@NonNull View rootView) {
            super(rootView);

            headerTextView = rootView.findViewById(R.id.header_text_view);
        }

        void setHeader(String header) {
            headerTextView.setText(header);
        }
    }
}