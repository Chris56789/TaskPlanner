package com.azure.cloudapp.westeurope.chrisserver2.takenplanner;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chris van der Werf on 11/5/2017.
 */

public class CreateTaskActivity extends Activity {
    private TextView TaskName;
    private NumberPicker Priority;
    private Button bt;
    private String KEY_TASKNAME = "taskname";
    private String KEY_PRIORITY = "priority";
    private String KEY_DEADLINE = "deadline";
    private Date date = null;
    private NumberPicker numpicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);

        TaskName = (TextView) findViewById(R.id.SelectedTaskName);

        bt = (Button) findViewById(R.id.SelectDateButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
            }
        });

        numpicker = (NumberPicker) findViewById(R.id.SelectedPriority);
        numpicker.setMinValue(1);
        numpicker.setMaxValue(3);
        numpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                numpicker.setValue(i1);
            }
        });


    }

    public void addTask(View view) {
        MyDBHandler db = new MyDBHandler(this,null,null,1);
        if (this.date == null) {
            Toast.makeText(getApplicationContext(), "Er is geen datum geselecteerd.", Toast.LENGTH_SHORT).show();
        }
        else if (TaskName.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "De taaknaam is leeg.", Toast.LENGTH_SHORT).show();
        }
        else {
            db.addTask(new Task(0,String.valueOf(TaskName.getText()), numpicker.getValue(), date));
            Intent intent = new Intent(CreateTaskActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        db.close();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            Calendar now = Calendar.getInstance();
            return new DatePickerDialog(this, mylistener, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        }
        return super.onCreateDialog(id);
    }

    private DatePickerDialog.OnDateSetListener mylistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            TextView deadline = (TextView) findViewById(R.id.SelectedDate);
            deadline.setText(i2 + "-" + (i1 + 1) + "-" + i);
            date = new Date(i,i1,i2);
        }
    };
}
