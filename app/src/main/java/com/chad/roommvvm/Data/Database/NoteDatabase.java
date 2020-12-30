package com.chad.roommvvm.Data.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
            ).addCallback(callback).fallbackToDestructiveMigration().build();
        }
        return noteDatabase;
    }

    public abstract NoteDao noteDao();

    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBTask(noteDatabase).execute();
        }
    };

    private static class PopulateDBTask extends AsyncTask<Void, Void, Void> {
        private final NoteDao noteDao;

        private PopulateDBTask(NoteDatabase noteDatabase) {
            this.noteDao = noteDatabase.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insertNote(new Note("Title 1", "Description 1", 1));
            noteDao.insertNote(new Note("Title 2", "Description 2", 5));
            noteDao.insertNote(new Note("Title 3", "Description 3", 7));
            return null;
        }

    }
}
