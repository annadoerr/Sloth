package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class EditList extends AppCompatActivity {

    EditText enterTitle;
    CheckBox content;
    Button date;
    Button time;
    Calendar calendar = Calendar.getInstance();
    DataBase db;
    ListItem listItem;
    ToDoItem toDoItem;
    List<ToDoItem> toDoItems;
    RecyclerView recyclerView;
    ToDoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID", 0);
        Long todoId = intent.getLongExtra("ID", 0);
        db = new DataBase(this);
        listItem = db.getItem(id);
        toDoItem = db.getTodo(todoId);

        //Get DataBase Data
        toDoItems= db.getallTodos();
        recyclerView = findViewById(R.id.editView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ToDoAdapter(this,toDoItems);
        recyclerView.setAdapter(adapter);

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
        content = findViewById(R.id.checkbox);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);

        enterTitle.setText(listItem.gettitle());
        date.setText(listItem.getDate());
        time.setText(listItem.getTime());

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
            int id = db.editList(listItem);
            int todoId = db.editTodo(toDoItem);
            if(id == listItem.getID() && todoId == toDoItem.getTodo_id()) {
                Toast.makeText(this, "List updated" , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("ID", listItem.getID());
            intent.putExtra("ToDoID", toDoItem.getTodo_id());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
