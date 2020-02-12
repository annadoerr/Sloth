package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedbackOneCheckbox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_one_checkbox);
    }

    // Goes back to selected list
    public void backToEdit(View v) {
        finish();
    }
}
