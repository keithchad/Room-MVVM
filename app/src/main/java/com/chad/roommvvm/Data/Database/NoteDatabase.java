package com.chad.roommvvm.Data.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.chad.roommvvm.Data.Model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public static NoteDatabase noteDatabase;

    public static synchronized NoteDatabase getInstance(Context context) {
        if (noteDatabase == null) {
            noteDatabase = Room.databaseBuilder(
                    context,
                    NoteDatabase.class,
                    "note_database"
            ).build();
        }
        return noteDatabase;
    }

    public abstract NoteDao noteDao();
}
