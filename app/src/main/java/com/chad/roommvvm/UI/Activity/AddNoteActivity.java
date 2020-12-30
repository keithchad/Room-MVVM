package com.chad.roommvvm.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;

import com.chad.roommvvm.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddNoteActivity extends AppCompatActivity {

    private TextInputEditText edittextTitle;
    private TextInputEditText edittextAuthor;
    private NumberPicker numberPicker;
    private MaterialButton materialButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initialize();
    }

    private void initialize() {
        edittextAuthor = findViewById(R.id.edittextDescription);
        edittextTitle = findViewById(R.id.edittextTitle);
        numberPicker = findViewById(R.id.numberPicker);
        materialButton = findViewById(R.id.buttonAdd);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
    }
}