package com.example.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;

public class TaskItemView extends ConstraintLayout{
    private AppCompatImageView iconImageView;
    private AppCompatTextView titleTextView;
    private AppCompatTextView subtitleTextView;

    private String title;
    private String subtitle;
    @DrawableRes
    private int backgroundResId;

    public TaskItemView(Context context) {
        this(context,null);
    }

    public TaskItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TaskItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(final AttributeSet attrs){
        inflate(getContext(),R.layout.task_item_view,this);
        iconImageView = findViewById(R.id.task_item_icon);
        titleTextView = findViewById(R.id.task_item_title);
        subtitleTextView = findViewById(R.id.task_item_subtitle);
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.TaskItemView,0,0);
        try {
            title = a.getString(R.styleable.TaskItemView_title);
            subtitle = a.getString(R.styleable.TaskItemView_subtitle);
            backgroundResId = a.getResourceId(
                    R.styleable.TaskItemView_backgroundDrawable,
                    R.drawable.ic_person
            );

            this.titleTextView.setText(title);
            this.subtitleTextView.setText(subtitle);
            setBackgroundResId(backgroundResId);
        } finally {
            a.recycle();
        }
    }


    @Nullable
    public AppCompatImageView getIcon() {
        return iconImageView;
    }


    @Nullable public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.titleTextView.setText(title);
        redrawView();
    }

    public void setTitle(int color, String title) {
        this.title = title;
        this.titleTextView.setText(title);
        this.titleTextView.setTextColor(color);
        redrawView();
    }

    @Nullable public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        this.subtitleTextView.setText(subtitle);
        redrawView();
    }

    public void setSubtitle(int color, String subtitle) {
        this.subtitle = subtitle;
        this.subtitleTextView.setText(subtitle);
        this.subtitleTextView.setTextColor(color);
        redrawView();
    }

    public AppCompatTextView getTitleTextView() {
        return titleTextView;
    }

    public AppCompatTextView getSubtitleTextView() {
        return subtitleTextView;
    }

    public void setBackgroundResId(@DrawableRes int backgroundResId) {
        this.backgroundResId = backgroundResId;
        setBackgroundResource(backgroundResId);
        redrawView();
    }

    private void redrawView() {
        requestLayout();
        invalidate();
    }

}
