package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyTodo extends AppCompatActivity {

    DataBase db;
    ListItem listItem;
    TextView todos;
    Calendar calendar;
    String todaysDate;
    String currentTime;
    String currentDateTime;
    String setTime;
    String setDate;
    String dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_todo);
        //Adds AppCompbat Toolbar to Activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //Changes AppBar Title
        getSupportActionBar().setTitle(R.string.todoTitle);
        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Changes StatusBar Color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        todos = findViewById(R.id.todos);

        //ID einer Liste von Adapter bekommen
        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID", 0);

        //Daten von Datenbank holen
        db = new DataBase(this);
        listItem = db.getItem(id);
        getSupportActionBar().setTitle(listItem.gettitle());
        todos.setText(listItem.getContent());
        todos.setMovementMethod(new ScrollingMovementMethod());

        //Feedback Screen aufrufen, wenn Deadline überschritten wurde
        //Preparing set Date and current Date
        setTime = listItem.getTime();
        setDate = listItem.getDate();
        dateTime =setTime+" "+setDate;

        calendar = Calendar.getInstance();
        todaysDate = calendar.get(Calendar.DAY_OF_MONTH)+"."+calendar.get(Calendar.MONTH + 1)+"."+calendar.get(Calendar.YEAR);
        currentTime = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        currentDateTime = todaysDate+" "+currentTime;

        try {
            //Parsing dates for comparing
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = sdf.parse(setDate);
            Date date2 = sdf.parse(todaysDate);

            //Comparing the dates
            if(date1.before(date2)){
                Log.e("app", "Date1 is before Date2");
            }

            if(date1.after(date2)){
                Log.e("app", "Date1 is after Date2");
                Intent i = new Intent(this, FeedbackOne.class);
                startActivity(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.edit) {
           //Nutzer zur EditActivity schicken
            Toast.makeText(this, "Edit",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, EditList.class);
            intent.putExtra("ID",listItem.getID());
            startActivity(intent);

        }
        if(item.getItemId() == R.id.delete) {

            db.deleteList(listItem.getID());
            Toast.makeText(getApplicationContext(),getString(R.string.deleted) , Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
