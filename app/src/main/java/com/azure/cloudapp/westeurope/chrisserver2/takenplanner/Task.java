package com.azure.cloudapp.westeurope.chrisserver2.takenplanner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chris van der Werf on 11/5/2017.
 */

class Task {
    private int _id;
    private String _name;
    private int _priority;
    private Date _deadline;

    public Task() {}

    public int get_id() {
        return _id;
    }

    public Task(int id, String name, int priority, Date deadline) {
        this._id = id;
        this._name = name;
        this._priority = priority;
        this._deadline = deadline;
    }

    public String get_name() {
        return _name;
    }


    public int get_priority() {
        return _priority;
    }

    public Date get_deadline() {
        return _deadline;
    }

    public String get_deadline_toString() {
        Date date = this._deadline;
        return date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getYear();
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_priority(int _priority) {
        this._priority = _priority;
    }

    public void set_deadline(Date date) {
        this._deadline = date;
    }

    public void set_deadline(long date) {
        this._deadline = new Date(date);
    }

    public String toString() {
        String base = "";
        base += "Taak: " + this.get_name() + "\n";
        base += "Prioriteit: " + this.get_priority() + "\n";
        base += "Deadline: " + this.get_deadline_toString() + "\n";
        return base;
    }
}
