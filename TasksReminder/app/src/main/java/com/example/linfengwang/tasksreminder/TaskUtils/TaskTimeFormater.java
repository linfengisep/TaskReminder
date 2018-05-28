package com.example.linfengwang.tasksreminder.TaskUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskTimeFormater {
    public static long getEpochTime(String ddMMYYYYHHMM) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyy HH mm");
        Date date = dateFormat.parse(ddMMYYYYHHMM);
        return date.getTime();
    }
}
