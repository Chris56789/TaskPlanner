package com.azure.cloudapp.westeurope.chrisserver2.takenplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Chris van der Werf on 11/6/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "tasks.db";
    private static final String TABLE_TASKS = "tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASKNAME = "name";
    private static final String COLUMN_PRIORITY = "priority";
    private static final String COLUMN_DEADLINE = "deadline";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_TASKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASKNAME + " TEXT, " +
                COLUMN_PRIORITY + " INTEGER, " +
                COLUMN_DEADLINE + " DATE " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME, task.get_name());
        values.put(COLUMN_PRIORITY, task.get_priority());
        values.put(COLUMN_DEADLINE, task.get_deadline().getTime());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

    public void deleteTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TASKS + " WHERE " + COLUMN_ID + "=\"" + task.get_id() + "\";");
    }

    public ArrayList<Task> databasetoTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>(0);
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TASKS + " WHERE 1";
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            Task task = new Task();
            task.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
            task.set_name(c.getString(c.getColumnIndex(COLUMN_TASKNAME)));
            task.set_priority(c.getInt(c.getColumnIndex(COLUMN_PRIORITY)));
            task.set_deadline(c.getLong(c.getColumnIndex(COLUMN_DEADLINE)));
            tasks.add(task);
            c.moveToNext();
        }
        db.close();
        return tasks;
    }

}
