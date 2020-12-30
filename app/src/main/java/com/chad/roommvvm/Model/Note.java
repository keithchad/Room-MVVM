package com.chad.roommvvm.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.chad.roommvvm.Constants.Constants;

@Entity(tableName = Constants.TABLE_NAME)
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = )
    private int priority;
}
