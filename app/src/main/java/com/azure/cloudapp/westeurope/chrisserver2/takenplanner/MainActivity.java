package com.azure.cloudapp.westeurope.chrisserver2.takenplanner;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private MyDBHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDBHandler(this,null,null,1);
        listview = (ListView) findViewById(R.id.ListViewTasks);
        listview.setAdapter(new TaskAdapter(this, db.databasetoTasks()));

        listview.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                db.deleteTask((Task)listview.getItemAtPosition(i));
                recreate();
                return true;
            }
        });

    }

    public void addTask(View view) {
        Intent k = new Intent(MainActivity.this, CreateTaskActivity.class);
        startActivity(k);
    }
}
