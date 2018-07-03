package com.example.database.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.database.Entity.TaskItem;

public class TaskStatusConverter {
    @TypeConverter
    public static TaskItem.TaskStatus fromInteger(int value){
        return value == 1? TaskItem.TaskStatus.DONE: TaskItem.TaskStatus.UNDONE;
    }
    @TypeConverter
    public static int toInteger(TaskItem.TaskStatus status){
        return status == TaskItem.TaskStatus.DONE? 1:0;
    }
}
