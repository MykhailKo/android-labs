package com.example.lab1app;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class UserActionEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "log")
    public String log;

    @ColumnInfo(name = "log_date")
    public String log_date;

    @Override
    public String toString() {
        return log_date.toString() + ":" + log;
    }
}
