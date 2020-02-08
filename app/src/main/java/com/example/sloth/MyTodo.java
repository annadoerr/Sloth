package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyTodo extends AppCompatActivity {

    DataBase db;
    ListItem listItem;
    TextView todo, todo1, todo2, todo3, todo4, todo5;
    CheckBox checkBox, checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    String checkedValue, checkedValue1, checkedValue2, checkedValue3, checkedValue4, checkedValue5;
    String one;
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
        checkBox = findViewById(R.id.checkbox);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);
        checkBox5 = findViewById(R.id.checkbox5);


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
        //Set CheckBox to checked if value was saved as 1
        one = "1";
        //Checkbox
        checkedValue = listItem.getIsDone();
        if(checkedValue.equals(one)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        //Checkbox1
        checkedValue1 = listItem.getIsDone1();
        if(checkedValue1.equals(one)) {
            checkBox1.setChecked(true);
        } else {
            checkBox1.setChecked(false);
        }

        //Checkbox2
        checkedValue2 = listItem.getIsDone2();
        if(checkedValue2.equals(one)) {
            checkBox2.setChecked(true);
        } else {
            checkBox2.setChecked(false);
        }

        //Checkbox3
        checkedValue3 = listItem.getIsDone3();
        if(checkedValue3.equals(one)) {
            checkBox3.setChecked(true);
        } else {
            checkBox3.setChecked(false);
        }

        //Checkbox4
        checkedValue4 = listItem.getIsDone4();
        if(checkedValue4.equals(one)) {
            checkBox4.setChecked(true);
        } else {
            checkBox4.setChecked(false);
        }

        //Checkbox5
        checkedValue5 = listItem.getIsDone5();
        if(checkedValue5.equals(one)) {
            checkBox5.setChecked(true);
        } else {
            checkBox5.setChecked(false);
        }

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
