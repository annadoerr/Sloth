package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

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
    TextView todo;
    TextView todo1;
    TextView todo2;
    TextView todo3;
    TextView todo4;
    TextView todo5;
    Calendar calendar;
    String currentDate;
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

        todo = findViewById(R.id.todoText);
        todo1 = findViewById(R.id.todoText1);
        todo2 = findViewById(R.id.todoText2);
        todo3 = findViewById(R.id.todoText3);
        todo4 = findViewById(R.id.todoText4);
        todo5 = findViewById(R.id.todoText5);

        //ID einer Liste von Adapter bekommen
        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID", 0);

        //Daten von Datenbank holen
        db = new DataBase(this);
        listItem = db.getItem(id);
        getSupportActionBar().setTitle(listItem.gettitle());
        todo.setText(listItem.getTodo());
        todo1.setText(listItem.getTodo1());
        todo2.setText(listItem.getTodo2());
        todo3.setText(listItem.getTodo3());
        todo4.setText(listItem.getTodo4());
        todo5.setText(listItem.getTodo5());

        //Feedback Screen aufrufen, wenn Deadline überschritten wurde
        //Preparing set Date and current Date
        setTime = listItem.getTime();
        setDate = listItem.getDate();
        dateTime =setDate+" "+setTime;

        //Get Todays Date and Time
        calendar = Calendar.getInstance();
        currentDate = calendar.get(Calendar.DAY_OF_MONTH)+"."+(calendar.get(Calendar.MONTH)+1)+"."+calendar.get(Calendar.YEAR);
        currentTime = zero(calendar.get(Calendar.HOUR))+":"+zero(calendar.get(Calendar.MINUTE));
        currentDateTime = currentDate+" "+currentTime;

        Log.d("calendar", "Date and Time " + currentDate + "and" + currentTime);
        Log.d("calendar2", "Date" + setDate);
        Log.d("calendar1", "Time" + setTime);
        Log.d("calendar3", "dateTime" + dateTime);
        Log.d("calendar4","currentDateTime" + currentDateTime);

        try {
            //Parsing dates for comparing
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = sdf.parse(setDate);
            Date date2 = sdf.parse(currentDate);

            //Comparing the dates
            if(date1.before(date2)){
                Log.e("app", "Date1 is before Date2");
            }

            if(date1.after(date2)){
                Log.e("app", "Date1 is after Date2");
                Intent i = new Intent(this, FeedbackOne.class);
                i.putExtra("ID",listItem.getID());
                startActivity(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String zero(int i) {
        if(i<10) {
            return "0" + i;
        }else{
            return String.valueOf(i);
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
