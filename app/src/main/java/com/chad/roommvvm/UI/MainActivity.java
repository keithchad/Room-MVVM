package com.chad.roommvvm.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.roommvvm.Data.Adapter.NoteAdapter;
import com.chad.roommvvm.Data.Constants.Constants;
import com.chad.roommvvm.Data.Interface.NoteInterface;
import com.chad.roommvvm.Data.Model.Note;
import com.chad.roommvvm.Data.ViewModel.NoteViewModel;
import com.chad.roommvvm.R;
import com.chad.roommvvm.UI.Activity.AddNoteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteInterface {

    private NoteAdapter noteAdapter;
    private RecyclerView recyclerView;
    public NoteViewModel noteViewModel;
    private List<Note> list;
    private FloatingActionButton buttonAdd;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        buttonAdd = findViewById(R.id.buttonAddNote);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteAdapter = new NoteAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        buttonAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivityForResult(intent, Constants.ADD_NOTE_REQUEST);
        });

        getNotes();
    }

    private void getNotes() {
        noteViewModel.getAllNotes().observe(this, notes -> noteAdapter.getNotes(notes));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(Constants.TITLE);
            String description = data.getStringExtra(Constants.DESCRIPTION);
            int priority = data.getIntExtra(Constants.PRIORITY, 1);

            Note note = new Note(title, description, priority);
            noteViewModel.insertNote(note);

            Snackbar.make(coordinatorLayout, "Note Saved", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(coordinatorLayout, "Note not Saved", Snackbar.LENGTH_SHORT).show();
        }
    }
}