package com.example.database.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.database.TaskPriority;

public final class TaskPriorityConverter {
    private TaskPriorityConverter(){}

    @TypeConverter
    public static TaskPriority fromInteger(Integer level){
        switch (level){
            case 1:
                return TaskPriority.LOW;
            case 2:
                return TaskPriority.MIDDLE;
            case 3:
                return TaskPriority.HIGH;
                default:return TaskPriority.LOW;
        }
    }

    @TypeConverter
    public static Integer toInteger(TaskPriority taskPriority){
        switch (taskPriority){
            case LOW:return 1;
            case MIDDLE:return 2;
            case HIGH:return 3;
            default:return 1;
        }
    }

}
