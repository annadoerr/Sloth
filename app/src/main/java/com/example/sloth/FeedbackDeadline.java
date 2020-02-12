package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class FeedbackDeadline extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_deadline);

    }

    // Goes back to selected list
    public void backToMyTodo(View v) {
       finish();
    }
}
