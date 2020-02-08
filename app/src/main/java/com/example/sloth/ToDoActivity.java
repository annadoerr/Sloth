package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
public class ToDoActivity extends AppCompatActivity {

    EditText enterTitle;
    Button date;
    Button time;
    EditText enterTodo, enterTodo1, enterTodo2, enterTodo3, enterTodo4, enterTodo5;
    CheckBox checkBox, checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    String checkedValue, checkedValue1, checkedValue2, checkedValue3, checkedValue4, checkedValue5;
    String checkedNumber;
    int count = 0;
    String one;
    Calendar calendar = Calendar.getInstance();
    String currentDate;
    String currentTime;
    ListItem listItem;
    DataBase db;

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


        //Gets Title and ToDos, Date and Time
        enterTitle = findViewById(R.id.enterTitle);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        enterTodo = findViewById(R.id.todoText);
        enterTodo1 = findViewById(R.id.todoText1);
        enterTodo2 = findViewById(R.id.todoText2);
        enterTodo3 = findViewById(R.id.todoText3);
        enterTodo4 = findViewById(R.id.todoText4);
        enterTodo5 = findViewById(R.id.todoText5);
        checkBox = findViewById(R.id.checkbox);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);
        checkBox5 = findViewById(R.id.checkbox5);
        checkedValue = "0";
        checkedValue1 = "0";
        checkedValue2 = "0";
        checkedValue3 = "0";
        checkedValue4 = "0";
        checkedValue5 = "0";


        // CheckBox onchanged Listener
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue = "1";
                } else {
                   checkedValue = "1";
                }
            }
        });

        // CheckBox1 onchanged Listener
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue1 = "1";
                } else {
                    checkedValue1 = "1";
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue2 = "1";
                } else {
                    checkedValue2 = "1";
                }
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue3 = "1";
                } else {
                    checkedValue3 = "1";
                }
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue4 = "1";
                } else {
                    checkedValue4 = "1";
                }
            }
        });

        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkedValue5 = "1";
                } else {
                    checkedValue5 = "0";
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

                        setDate.setText(dayOfMonth+"."+(month + 1)+"."+year);

                    }
                },calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

    }

    public void countChecks() {
        //Count number of checked Checkboxes
        one = "1";
        if(checkedValue.equals(one)) {
            count++;
        } if (checkedValue1.equals(one)) {
            count ++;
        } if (checkedValue2.equals(one)) {
            count ++;
        } if (checkedValue3.equals(one)) {
            count ++;
        } if (checkedValue4.equals(one)) {
            count ++;
        } if (checkedValue5.equals(one)) {
            count ++;
        }
        checkedNumber = String.valueOf(count);
        Log.d("count2", "countInt" +" und " + count);
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
            countChecks();
            listItem = new ListItem(enterTitle.getText().toString(), date.getText().toString(), time.getText().toString(), enterTodo.getText().toString(),
                    enterTodo1.getText().toString(), enterTodo2.getText().toString(), enterTodo3.getText().toString(), enterTodo4.getText().toString(),
                    enterTodo5.getText().toString(), checkedValue, checkedValue1, checkedValue2, checkedValue3, checkedValue4, checkedValue5, checkedNumber);
            Log.d("count", "checkBoxCount" + checkedNumber);
            db = new DataBase(this);
            db.addItem(listItem);
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
            gotToMain();
        }
        return super.onOptionsItemSelected(item);
    }

    public void gotToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
