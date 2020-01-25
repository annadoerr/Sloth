package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.List;

public class ToDoActivity extends AppCompatActivity {

    EditText enterTitle;
    EditText content;
    Button date;
    Button time;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        //Adds AppCompbat Toolbar to Activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //Changes AppBar Title
        getSupportActionBar().setTitle(R.string.todoTitle);
        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Changes StatusBar Color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        //Gets Title and ToDos
        enterTitle = findViewById(R.id.enterTitle);
        date = findViewById(R.id.time);
        time = findViewById(R.id.date);

        enterTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length() != 0) {
                        getSupportActionBar().setTitle(s);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Time Picker
        final Button setTime = findViewById(R.id.time);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(ToDoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        setTime.setText(hourOfDay + ":" + minute);

                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(ToDoActivity.this));

                timePickerDialog.show();
            }
        });

        //Date Picker
        final Button setDate = findViewById(R.id.date);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ToDoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        setDate.setText(dayOfMonth + "." + (month + 1) + "." + year);

                    }
                },calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

    }

    //Adds save option for note
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save) {
            ListItem listItem = new ListItem(enterTitle.getText().toString(), time.getText().toString(), date.getText().toString());
            DataBase db = new DataBase(this);
            db.addNote(listItem);
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
