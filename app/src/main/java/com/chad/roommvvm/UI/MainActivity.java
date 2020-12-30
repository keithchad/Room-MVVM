package com.chad.roommvvm.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.roommvvm.Data.Adapter.NoteAdapter;
import com.chad.roommvvm.Data.Interface.NoteInterface;
import com.chad.roommvvm.Data.Model.Note;
import com.chad.roommvvm.Data.ViewModel.NoteViewModel;
import com.chad.roommvvm.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteInterface {

    private NoteAdapter noteAdapter;
    private RecyclerView recyclerView;
    public NoteViewModel noteViewModel;
    private List<Note> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteAdapter = new NoteAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        getNotes();
    }

    private void getNotes() {
        noteViewModel.getAllNotes().observe(this, notes -> noteAdapter.getNotes(notes));
    }
}