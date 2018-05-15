package com.example.customviews;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;

public class UserCardView extends ConstraintLayout {

    private SimpleDraweeView iconImageView;
    private AppCompatTextView titleTextView;
    private AppCompatTextView subtitleTextView;

    private String title;
    private String subtitle;
    private String iconUrl;
    @DrawableRes private int backgroundResId;

    public UserCardView(Context context) {
        this(context, null);
    }

    public UserCardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            initializeView(attrs);
        }
    }

    private void initializeView(final AttributeSet attrs) {
        inflate(getContext(), R.layout.user_card_view, this);

        iconImageView = findViewById(R.id.card_icon);
        titleTextView = findViewById(R.id.card_title);
        subtitleTextView = findViewById(R.id.card_subtitle);

        TypedArray a = getContext().getTheme()
                .obtainStyledAttributes(attrs, R.styleable.UserCardView, 0, 0);

        try {
            title = a.getString(R.styleable.UserCardView_title);
            subtitle = a.getString(R.styleable.UserCardView_subtitle);
            iconUrl = a.getString(R.styleable.UserCardView_iconUrl);
            backgroundResId = a.getResourceId(
                    R.styleable.UserCardView_backgroundDrawable,
                    R.drawable.ic_person
            );

            this.titleTextView.setText(title);
            this.subtitleTextView.setText(subtitle);
            if (iconUrl != null) {
                setIconUrl(iconUrl);
            }
            setBackgroundResId(backgroundResId);
        } finally {
            a.recycle();
        }
    }

    @Nullable public SimpleDraweeView getIcon() {
        return iconImageView;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        iconImageView.setImageURI(iconUrl);
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
