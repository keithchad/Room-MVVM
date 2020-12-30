package com.chad.roommvvm.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.chad.roommvvm.Data.Constants.Constants;
import com.chad.roommvvm.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddNoteActivity extends AppCompatActivity {

    private TextInputEditText edittextTitle;
    private TextInputEditText edittextDescription;
    private NumberPicker numberPicker;
    private MaterialButton materialButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initialize();
    }

    private void initialize() {
        edittextDescription = findViewById(R.id.edittextDescription);
        edittextTitle = findViewById(R.id.edittextTitle);
        numberPicker = findViewById(R.id.numberPicker);
        materialButton = findViewById(R.id.buttonAdd);

        materialButton.setOnClickListener(v -> saveNote());

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
    }

    private void saveNote() {
        String title = edittextTitle.getText().toString();
        String description = edittextDescription.getText().toString();
        int priority = numberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = getIntent();
        data.putExtra(Constants.DESCRIPTION, description);
        data.putExtra(Constants.TITLE, title);
        data.putExtra(Constants.PRIORITY, priority);

        setResult(RESULT_OK, data);
        finish();
    }
}