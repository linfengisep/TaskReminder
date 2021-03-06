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
    private static final int LOW=1;
    private static final int MIDDLE=2;
    private static final int HIGH=3;

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

        plusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementValue();
            }
        });

        minusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementValue();
            }
        });
    }

    private void incrementValue() {
        currentPriorityValue = getPriorityValue(priorityValueView.getText().toString());
        if(currentPriorityValue < HIGH) {
            priorityValueView.setText(getPriorityString(++currentPriorityValue));
        }
    }

    private void decrementValue() {
        currentPriorityValue = getPriorityValue(priorityValueView.getText().toString());
        if(currentPriorityValue > LOW) {
            priorityValueView.setText(getPriorityString(--currentPriorityValue));
        }
    }

    public String getPriorityString(int level){
        String priorityLevel=null;
        switch (level){
            case LOW:priorityLevel =getContext().getString(R.string.low);
            break;
            case MIDDLE:priorityLevel =getContext().getString(R.string.middle);
            break;
            case HIGH:priorityLevel=getContext().getString(R.string.high);
            default:break;
        }
        return priorityLevel;
    }

    public int getPriorityValue(String valueString){
        int levelPriority=0;
        if(valueString.toLowerCase().equals(getContext().getString(R.string.low))){
            levelPriority =1;
        }else if(valueString.toLowerCase().equals(getContext().getString(R.string.middle))){
            levelPriority =2;
        }else if(valueString.toLowerCase().equals(getContext().getString(R.string.high))){
            levelPriority =3;
        }
        return levelPriority;
    }

    /**
     * Get the current value
     *
     * @return the current value
     */
    public int getValue() {
        return getPriorityValue(priorityValueView.getText().toString());
    }

    /**
     * Set the current value.  If the passed in value exceeds the current min or max, the value
     * will be set to the respective min/max.
     *
     * @param newValue new value
     */
    public void setValue(int newValue) {
        int value = newValue;
        if(newValue < LOW) {
            value = LOW;
        } else if (newValue > HIGH) {
            value = HIGH;
        }
        priorityValueView.setText(getPriorityString(value));
    }

 /*
    interface OnPlusPriorityLevelListener{
        void onPlusPriorityLevel(int priorityLevel);
    }

    interface OnMinusPriorityLevelListener{
        void onMinusPriorityLevel(int priorityLevel);
    }

    public void setOnClickPlusPriorityLevel(OnPlusPriorityLevelListener listener){
        this.plusPriorityLevelListener = listener;
    }

    public void setOnClickMinusPriorityLevel(OnMinusPriorityLevelListener listener){
        this.minusPriorityLevelListener = listener;
    }

    public class PlusButtonClickCallback implements OnClickListener{

        @Override
        public void onClick(View v) {
            incrementValue();
            plusPriorityLevelListener.onPlusPriorityLevel(currentPriorityValue);
        }
    }

    public class MinusButtonClickCallback implements OnClickListener{

        @Override
        public void onClick(View v) {
            decrementValue();
            minusPriorityLevelListener.onMinusPriorityLevel(currentPriorityValue);
        }
    }
*/
}
