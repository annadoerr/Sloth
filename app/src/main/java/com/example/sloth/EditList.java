package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import java.util.Calendar;

public class EditList extends AppCompatActivity {

    // Initialize Variables
    EditText enterTitle;
    EditText todo, todo1, todo2, todo3, todo4, todo5;
    CheckBox checkBox, checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    String checkedValue, checkedValue1, checkedValue2, checkedValue3, checkedValue4, checkedValue5;
    String one;
    String checkedNumber;
    int count = 0;
    Button date;
    Button time;
    Calendar calendar = Calendar.getInstance();
    DataBase db;
    ListItem listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        // Get ID
        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID", 0);
        db = new DataBase(this);
        listItem = db.getItem(id);

        // Adds AppCompbat Toolbar to Activity
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //Changes AppBar Title
        getSupportActionBar().setTitle(listItem.gettitle());
        // Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Changes StatusBar Color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        // Assign variables to Views in activity_edit_list
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
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);
        checkBox5 = findViewById(R.id.checkbox5);

        // Assigns title and todos to ListItem class title and todos
        enterTitle.setText(listItem.gettitle());
        date.setText(listItem.getDate());
        time.setText(listItem.getTime());
        todo.setText(listItem.getTodo());
        todo1.setText(listItem.getTodo1());
        todo2.setText(listItem.getTodo2());
        todo3.setText(listItem.getTodo3());
        todo4.setText(listItem.getTodo4());
        todo5.setText(listItem.getTodo5());

        // Set CheckBox to checked if value was saved as 1
        one = "1";
        //Checkbox
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
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkedValue5 = "1";
                    checkboxFeedback();
                } else {
                    checkedValue5 = "0";
                }
            }
        });

        // Changes App Bar Title to title in enterTitle
        enterTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
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

                        setTime.setText(String.format("%02d:%02d", hourOfDay, minute));

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
                }, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
    }

    // Feedback for Checkbox checked
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
        // Count number of checked Checkboxes
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
        // Assigns number of checked Checkboxes to checkedNumber String
        checkedNumber = String.valueOf(count);
    }

    // Adds save menu in app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if date and time were picked
        if(TextUtils.isEmpty(date.getText().toString()) || TextUtils.isEmpty(time.getText().toString())) {
            // If date AND time were not picked, return the following text
            Toast.makeText(this, getString(R.string.fillOut), Toast.LENGTH_SHORT).show();
        } else {
            // If date and time were picked proceed to save edited Infos
            if (item.getItemId() == R.id.save) {
                countChecks();
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
                listItem.setIsDone1(checkedValue1);
                listItem.setIsDone2(checkedValue2);
                listItem.setIsDone3(checkedValue3);
                listItem.setIsDone4(checkedValue4);
                listItem.setIsDone5(checkedValue5);
                listItem.setCheckedNumber(checkedNumber);

                db.editList(listItem);
                Toast.makeText(this, getString(R.string.updated), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("ID", listItem.getID());
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
