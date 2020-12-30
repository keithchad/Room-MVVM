package com.chad.roommvvm.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.chad.roommvvm.Constants.Constants;
import com.chad.roommvvm.Model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY priority")
    List<Note> getAllNotes();

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("SELECT " + Constants.TABLE_NAME)
    void deleteAllNotes();

}
