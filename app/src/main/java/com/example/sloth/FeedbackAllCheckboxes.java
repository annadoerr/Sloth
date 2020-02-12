package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class FeedbackAllCheckboxes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_all_checkboxes);

    }

    // Goes back to selected list
    public void backToEdit(View v) {
        finish();
    }
}
