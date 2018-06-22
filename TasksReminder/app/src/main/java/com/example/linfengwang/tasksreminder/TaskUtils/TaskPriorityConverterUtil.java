package com.example.linfengwang.tasksreminder.TaskUtils;

import com.example.database.TaskPriority;

public class TaskPriorityConverterUtil {
    public static TaskPriority getTaskPriorityFromString(int value){
            switch (value){
                case 1:
                return TaskPriority.LOW;
                case 2:
                return TaskPriority.MIDDLE;
                case 3:
                return TaskPriority.HIGH;
            }
            return TaskPriority.LOW;
        }
    }
