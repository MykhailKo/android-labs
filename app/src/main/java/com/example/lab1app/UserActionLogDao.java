package com.example.lab1app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserActionLogDao {
    @Query("SELECT * from useractionentity ORDER BY log_date DESC")
    List<UserActionEntity> getAll();

    @Insert
    void insertAll(UserActionEntity... actions);

    @Delete
    void delete(UserActionEntity action);
}
