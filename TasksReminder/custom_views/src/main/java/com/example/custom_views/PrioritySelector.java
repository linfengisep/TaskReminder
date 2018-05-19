package com.example.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * This view to allow the select value by pressing plus/minus buttons.
 *
 */

public class PrioritySelector extends RelativeLayout{
    private final int low=1;
    private final int middle=2;
    private final int high=3;

    private ImageView plusBtn;
    private ImageView minusBtn;
    private TextView priorityValueView;
    private View rootView;

    private int currentPriorityValue=1;




    public PrioritySelector(Context context) {
        this(context,null);
    }

    public PrioritySelector(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PrioritySelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs){
        rootView = inflate(getContext(),R.layout.priority_selector,this);
        plusBtn = rootView.findViewById(R.id.plusButton);
        minusBtn = rootView.findViewById(R.id.minusButton);
        priorityValueView = rootView.findViewById(R.id.priorityView);

        minusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementValue();
            }
        });

        plusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementValue();
            }
        });
    }

    private void incrementValue() {
        currentPriorityValue = Integer.valueOf(priorityValueView.getText().toString());
        if(currentPriorityValue < high) {
            priorityValueView.setText(getPriority(currentPriorityValue + 1));
        }
    }

    private void decrementValue() {
        currentPriorityValue = Integer.valueOf(priorityValueView.getText().toString());
        if(currentPriorityValue > low) {
            priorityValueView.setText(getPriority(currentPriorityValue - 1));
        }
    }

    private String getPriority(int level){
        String priorityLevel=null;
        switch (level){
            case low:priorityLevel =getContext().getString(R.string.low);
            break;
            case middle:priorityLevel =getContext().getString(R.string.middle);
            break;
            case high:priorityLevel=getContext().getString(R.string.high);
            default:break;
        }
        return priorityLevel;
    }

    /**
     * Get the current value
     *
     * @return the current value
     */
    public int getValue() {
        return Integer.valueOf(priorityValueView.getText().toString());
    }

    /**
     * Set the current value.  If the passed in value exceeds the current min or max, the value
     * will be set to the respective min/max.
     *
     * @param newValue new value
     */
    public void setValue(int newValue) {
        int value = newValue;
        if(newValue < low) {
            value = low;
        } else if (newValue > high) {
            value = high;
        }
        priorityValueView.setText(getPriority(value));
    }

}
