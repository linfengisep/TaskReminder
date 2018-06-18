package com.example.linfengwang.tasksreminder.TaskUtils;

import com.example.database.TaskPriority;

public class TaskPriorityConverterUtil {
    public static TaskPriority getTaskPriorityFromString(String valueString){

            if(valueString.equals("low")){
                return TaskPriority.LOW;
            }else if(valueString.equals("middle")){
                return TaskPriority.MIDDLE;
            }else if(valueString.equals("high")){
                return TaskPriority.HIGH;
            }
            return TaskPriority.LOW;
        }
    }
