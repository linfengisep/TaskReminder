package com.example.custom_views;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class MessageEditView extends ConstraintLayout {

    private AppCompatEditText messageEditView;
    private AppCompatImageView sendButtonView;

    private int sendButtonImage;

    public MessageEditView(Context context) {
        this(context, null);
    }

    public MessageEditView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            init(attrs);
        }
    }

    private void init(AttributeSet attributeSet) {
        inflate(getContext(), R.layout.message_edit_view, this);

        messageEditView = findViewById(R.id.message_text);
        sendButtonView = findViewById(R.id.message_send_button);

        TypedArray at =
                getContext().getTheme()
                        .obtainStyledAttributes(attributeSet, R.styleable.MessageEditView, 0, 0);

        try {
            if (at.hasValue(R.styleable.MessageEditView_messageEditViewHint)) {
                String messageHintText = at.getString(R.styleable.MessageEditView_messageEditViewHint);
                messageEditView.setHint(messageHintText);
            }

            if (at.hasValue(R.styleable.MessageEditView_messageEditViewImage)) {
                sendButtonImage =
                        at.getResourceId(R.styleable.MessageEditView_messageEditViewImage,
                                R.drawable.ic_send_black);
                sendButtonView.setBackgroundResource(sendButtonImage);
            }
        } finally {
            at.recycle();
        }
    }

    private void reDraw() {
        invalidate();
        requestLayout();
    }

    public AppCompatEditText getMessageEditView() {
        return messageEditView;
    }

    public AppCompatImageView getSendButtonView() {
        return sendButtonView;
    }

    public void setSendButtonImage(int sendButtonImage) {
        sendButtonView.setBackgroundResource(sendButtonImage);
        this.sendButtonImage = sendButtonImage;
        reDraw();
    }
}