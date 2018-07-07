package com.example.linfengwang.tasksreminder.Fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.linfengwang.tasksreminder.R;

public class TaskDialogFragment extends DialogFragment{
    private static final String TASK_ID = "TASK_ID";
    private int taskId;

    static TaskDialogFragment newInstance(int taskId){
        TaskDialogFragment taskDialogFragment = new TaskDialogFragment();
        Bundle args = new Bundle();
        args.putInt(TASK_ID,taskId);
        taskDialogFragment.setArguments(args);
        return taskDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskId = getArguments().getInt(TASK_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.task_item_fragment_dialog, container, false);
        return v;
    }
}
