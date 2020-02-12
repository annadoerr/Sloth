package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyTodo extends AppCompatActivity {

    // Initialize Variables
    DataBase db;
    ListItem listItem;
    String title;
    String date;
    String time;
    TextView todo, todo1, todo2, todo3, todo4, todo5;
    CheckBox checkBox, checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    String checkedValue, checkedValue1, checkedValue2, checkedValue3, checkedValue4, checkedValue5;
    String one;
    String setTime;
    String setDate;
    String setDateTime;
    int count = 0;
    String checkedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_todo);
        // Adds AppCompbat Toolbar to Activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        // Changes AppBar Title
        getSupportActionBar().setTitle(R.string.todoTitle);
        // Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Changes StatusBar Color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        // Assign variables to Views in activity_my_to_do
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


        // Get ID from list
        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID", 0);

        // Get Data from Database
        db = new DataBase(this);
        listItem = db.getItem(id);
        getSupportActionBar().setTitle(listItem.gettitle());

        // Get Title, date, time to pass it on
        title = (listItem.gettitle());
        date = (listItem.getDate());
        time = (listItem).getTime();

        // Get to-do text to display it
        todo.setText(listItem.getTodo());
        todo1.setText(listItem.getTodo1());
        todo2.setText(listItem.getTodo2());
        todo3.setText(listItem.getTodo3());
        todo4.setText(listItem.getTodo4());
        todo5.setText(listItem.getTodo5());

        // Set CheckBox to checked if value was saved as 1
        one = "1";
        // Checkbox
        checkedValue = listItem.getIsDone();
        if (checkedValue.equals(one)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        // Checkbox1
        checkedValue1 = listItem.getIsDone1();
        if (checkedValue1.equals(one)) {
            checkBox1.setChecked(true);
        } else {
            checkBox1.setChecked(false);
        }

        // Checkbox2
        checkedValue2 = listItem.getIsDone2();
        if (checkedValue2.equals(one)) {
            checkBox2.setChecked(true);
        } else {
            checkBox2.setChecked(false);
        }

        // Checkbox3
        checkedValue3 = listItem.getIsDone3();
        if (checkedValue3.equals(one)) {
            checkBox3.setChecked(true);
        } else {
            checkBox3.setChecked(false);
        }

        // Checkbox4
        checkedValue4 = listItem.getIsDone4();
        if (checkedValue4.equals(one)) {
            checkBox4.setChecked(true);
        } else {
            checkBox4.setChecked(false);
        }

        // Checkbox5
        checkedValue5 = listItem.getIsDone5();
        if (checkedValue5.equals(one)) {
            checkBox5.setChecked(true);
        } else {
            checkBox5.setChecked(false);
        }

        // CheckBox on Change Listener and call checkBoxFeedback method
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue = "1";
                    checkboxFeedback();
                } else {
                    checkedValue = "0";
                }
            }
        });

        // CheckBox1 on Change Listener
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue1 = "1";
                    checkboxFeedback();
                } else {
                    checkedValue1 = "0";
                }
            }
        });

        // CheckBox2 on Change Listener
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue2 = "1";
                    checkboxFeedback();
                } else {
                    checkedValue2 = "0";
                }

            }
        });

        // CheckBox3 on Change Listener
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue3 = "1";
                    checkboxFeedback();
                } else {
                    checkedValue3 = "0";
                }
            }
        });

        // CheckBox4 on Change Listener
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue4 = "1";
                    checkboxFeedback();
                } else {
                    checkedValue4 = "0";
                }
            }
        });

        // CheckBox5 on Change Listener
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedValue5 = "1";
                    checkboxFeedback();
                } else {
                    checkedValue5 = "0";
                }
            }
        });

        // Compare set date and time with current date and time
        // Preparing set date and set time
        setTime = listItem.getTime();
        setDate = listItem.getDate();
        setDateTime = setDate +" "+ setTime;
        Log.d("setDateTime", setDateTime);

        // Get todays date and time
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String currentDateTime = formatter1.format(calendar1.getTime());
        Log.d("currentDate", "Date" + currentDateTime);

        try {
            // Parsing dates for comparing
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
            Date date1 = sdf.parse(setDateTime);
            Date date2 = sdf.parse(currentDateTime);

            // Comparing the dates
            if (date1.before(date2)) {
                if (checkBox.isChecked() && checkBox1.isChecked() && checkBox2.isChecked() &&
                        checkBox3.isChecked() && checkBox4.isChecked() && checkBox5.isChecked()) {
                    // Feedback if deadline has been met but all Checkboxes were checked
                    Log.e("app", "All Checkboxes were checked before Deadline");
                } else {
                    // Feedback if Deadline was missed and not all Checkboxes were checked
                    Log.e("app", "Date1 is after Date2.Deadline reached.");
                    Intent i = new Intent(this, FeedbackDeadline.class);
                    i.putExtra("ID", listItem.getID());
                    startActivity(i);
                }
            }
            if (date1.after(date2)) {
                // Feedback if Deadline hasn't been met yet
                Log.e("app", "Date1 is before Date2.Deadline not yet reached.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Feedback when one or all checkboxes are checked
    public void checkboxFeedback() {
        final Intent feedback = new Intent(this, FeedbackOneCheckbox.class);
        final Intent feedbackAll = new Intent(this, FeedbackAllCheckboxes.class);
        if (checkBox.isChecked() && checkBox1.isChecked() && checkBox2.isChecked() &&
                checkBox3.isChecked() && checkBox4.isChecked() && checkBox5.isChecked()) {
            // Feedback if all are checked
            startActivity(feedbackAll);
        } else {
            // Feedback if one is checked
            startActivity(feedback);
        }
    }

    public void countChecks() {
        //Count number of checked Checkboxes
        one = "1";
        if (checkedValue.equals(one)) {
            count++;
        }
        if (checkedValue1.equals(one)) {
            count++;
        }
        if (checkedValue2.equals(one)) {
            count++;
        }
        if (checkedValue3.equals(one)) {
            count++;
        }
        if (checkedValue4.equals(one)) {
            count++;
        }
        if (checkedValue5.equals(one)) {
            count++;
        }
        checkedNumber = String.valueOf(count);
    }

    // Adds edit menu in app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save) {
            countChecks();
            // Save changed Checkbox State and pass on title, date, time data
            listItem.setTitle(title);
            listItem.setDate(date);
            listItem.setTime(time);
            listItem.setTodo(todo.getText().toString());
            listItem.setTodo1(todo1.getText().toString());
            listItem.setTodo2(todo2.getText().toString());
            listItem.setTodo3(todo3.getText().toString());
            listItem.setTodo4(todo4.getText().toString());
            listItem.setTodo5(todo5.getText().toString());
            listItem.setIsDone(checkedValue);
            listItem.setIsDone1(checkedValue1);
            listItem.setIsDone2(checkedValue2);
            listItem.setIsDone3(checkedValue3);
            listItem.setIsDone4(checkedValue4);
            listItem.setIsDone5(checkedValue5);
            listItem.setCheckedNumber(checkedNumber);

            db.editList(listItem);
            Toast.makeText(this, getString(R.string.saved), Toast.LENGTH_SHORT).show();
            gotToMain();
        }
        if (item.getItemId() == R.id.edit) {
            // Send user to activity_edit_list
            Intent intent = new Intent(this, EditList.class);
            intent.putExtra("ID", listItem.getID());
            startActivity(intent);

        }
        if (item.getItemId() == R.id.delete) {
            // Delete list
            db.deleteList(listItem.getID());
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

            Toast.makeText(getApplicationContext(), getString(R.string.deleted), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    // Go to Main Activity method
    public void gotToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
