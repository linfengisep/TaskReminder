package com.example.database;

import android.content.Context;
import android.content.SharedPreferences;

public final class SharePreferenceManager {
    private final Context context;
    private final SharedPreferences sharePref;

    public SharePreferenceManager(Context context) {
        this.context = context;
        this.sharePref = this.context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE
        );
    }

    public void setTaskId(final int taskId) {
        SharedPreferences.Editor editor = sharePref.edit();
        editor.putInt(
                context.getString(R.string.task_id_flag),
                taskId
        );
        editor.apply();
    }

    public int getTaskId(){
        return sharePref.getInt(context.getString(R.string.task_id_flag),-1);
    }

}
