package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FeedbackOne extends AppCompatActivity {

    DataBase db;
    ListItem listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_one);

        Intent i = getIntent();
        Long id = i.getLongExtra("ID", 0);
        db = new DataBase(this);
        listItem = db.getItem(id);
    }

    public void backToMyTodo(View v) {
       Intent intent = new Intent(this, MainActivity.class);
       intent.putExtra("ID", listItem.getID());
       startActivity(intent);
    }
}
