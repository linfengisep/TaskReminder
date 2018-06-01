package com.example.database;

import com.example.database.Dao.TaskItemDao;

public interface DbComponent {
    SharePreferenceManager sharedPreferenceManager();
    TaskItemDao taskItemDao();
}
