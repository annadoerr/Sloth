package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EditList extends AppCompatActivity {

    EditText enterTitle;
    EditText todo, todo1, todo2, todo3, todo4, todo5;
    CheckBox checkBox;
    String checkedValue;
    String one;
    Button date;
    Button time;
    Calendar calendar = Calendar.getInstance();
    DataBase db;
    ListItem listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID", 0);
        db = new DataBase(this);
        listItem = db.getItem(id);

        //Adds AppCompbat Toolbar to Activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //Changes AppBar Title
        getSupportActionBar().setTitle(listItem.gettitle());
        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Changes StatusBar Color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        //Gets Title and ToDos
        enterTitle = findViewById(R.id.enterTitle);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        todo = findViewById(R.id.todoText);
        todo1 = findViewById(R.id.todoText1);
        todo2 = findViewById(R.id.todoText2);
        todo3 = findViewById(R.id.todoText3);
        todo4 = findViewById(R.id.todoText4);
        todo5 = findViewById(R.id.todoText5);
        checkBox = findViewById(R.id.checkbox);

        enterTitle.setText(listItem.gettitle());
        date.setText(listItem.getDate());
        time.setText(listItem.getTime());
        todo.setText(listItem.getTodo());
        todo1.setText(listItem.getTodo1());
        todo2.setText(listItem.getTodo2());
        todo3.setText(listItem.getTodo3());
        todo4.setText(listItem.getTodo4());
        todo5.setText(listItem.getTodo5());
        //Set CheckBox to checked if value was saved as 1
        checkedValue = listItem.getIsDone();
        one = "1";
        Log.d("Value", "checked?" +" + "+checkedValue);

        if(checkedValue.equals(one)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        // CheckBox onchanged Listener
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue = "1";
                } else {
                    checkedValue = "0";
                }
            }
        });

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

                TimePickerDialog timePickerDialog = new TimePickerDialog(EditList.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        setTime.setText(hourOfDay + ":" + minute);

                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(EditList.this));

                timePickerDialog.show();
            }
        });

        //Date Picker
        final Button setDate = findViewById(R.id.date);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditList.this, new DatePickerDialog.OnDateSetListener() {
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
            listItem.setTitle(enterTitle.getText().toString());
            listItem.setDate(date.getText().toString());
            listItem.setTime(time.getText().toString());
            listItem.setTodo(todo.getText().toString());
            listItem.setTodo1(todo1.getText().toString());
            listItem.setTodo2(todo2.getText().toString());
            listItem.setTodo3(todo3.getText().toString());
            listItem.setTodo4(todo4.getText().toString());
            listItem.setTodo5(todo5.getText().toString());
            listItem.setIsDone(checkedValue);
            int id = db.editList(listItem);
            if(id == listItem.getID()) {
                Toast.makeText(this, "List updated" , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("ID", listItem.getID());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
