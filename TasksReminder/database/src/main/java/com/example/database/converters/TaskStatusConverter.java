package com.example.database.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.database.TaskStatus;

public class TaskStatusConverter {
    @TypeConverter
    public static TaskStatus fromInteger(int value){
        return value == 1? TaskStatus.DONE:TaskStatus.UNDONE;
    }
    @TypeConverter
    public static int toInteger(TaskStatus status){
        return status == TaskStatus.DONE? 1:0;
    }
}
