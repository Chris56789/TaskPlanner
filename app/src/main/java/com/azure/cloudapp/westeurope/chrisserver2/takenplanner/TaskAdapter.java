package com.azure.cloudapp.westeurope.chrisserver2.takenplanner;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chris van der Werf on 11/7/2017.
 */

public class TaskAdapter extends BaseAdapter{
    private ArrayList<Task> tasks;
    private Context context;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.context = context;
    }
    @Override
    public int getCount() {
        return this.tasks.size();
    }

    @Override
    public Task getItem(int i) {
        return this.tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row, viewGroup, false);
        TextView taskname = (TextView) row.findViewById(R.id.TextViewTaskName);
        TextView taskpriority = (TextView) row.findViewById(R.id.TextViewTaskPriority);
        TextView taskdate = (TextView) row.findViewById(R.id.TextViewTaskDate);

        Task task = (Task) getItem(i);
        taskname.setText(task.get_name());
        taskpriority.setText(String.valueOf(task.get_priority()));
        taskdate.setText(task.get_deadline_toString());

        return row;
    }
}
