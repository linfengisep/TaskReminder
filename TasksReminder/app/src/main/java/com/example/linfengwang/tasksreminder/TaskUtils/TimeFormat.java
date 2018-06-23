package com.example.linfengwang.tasksreminder.TaskUtils;


import android.content.Context;

import com.example.linfengwang.tasksreminder.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class TimeFormat {
    static long currentTime = System.currentTimeMillis();
    private TimeFormat() {
    }

    public static String formatTime(Context context, long milliSecond) {
        long dayDifference = compareDifference(milliSecond, currentTime);
        if (dayDifference == 0) {
            return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date(milliSecond));
        } else if (dayDifference == 1) {
            return context.getString(R.string.yesterday);
        } else if (dayDifference > 1 && dayDifference < 7) {
            return new SimpleDateFormat("EEEE", Locale.getDefault()).format(new Date(milliSecond));
        } else if (dayDifference >= 7) {
            return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(milliSecond));
        } else {
            return "error";
        }
    }

    private static long compareDifference(long targetTime, long currentTime) {
        if(targetTime < currentTime){
            return getNumberOfDayFromEpoch(currentTime) - getNumberOfDayFromEpoch(targetTime);
        }else {
            return getNumberOfDayFromEpoch(targetTime)- getNumberOfDayFromEpoch(currentTime);
        }
    }

    private static long getNumberOfDayFromEpoch(long time) {
        long seconds = time / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        return days;
    }

    public static String formatTimeInFuture(Context context,long milliSecondInFuture){
        long dayDifference = compareDifference(milliSecondInFuture,currentTime);
        if(dayDifference == 0){
            return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date(milliSecondInFuture));
        } else if (dayDifference == 1) {
            return context.getString(R.string.yesterday);
        } else if (dayDifference > 1 && dayDifference < 7) {
            return new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date(milliSecondInFuture));
        } else if (dayDifference >= 7) {
            return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(milliSecondInFuture));
        } else {
            return "error";
        }
    }
}
