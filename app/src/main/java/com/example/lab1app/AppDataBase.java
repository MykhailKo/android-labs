package com.example.lab1app;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserActionEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserActionLogDao userActionDao();
}
