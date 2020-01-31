package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<ListItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adds AppCompbat Toolbar to Activity
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //Changes AppBar Title
        getSupportActionBar().setTitle(R.string.listTitle);

        //Changes StatusBar Color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        //Display Lists
        DataBase db = new DataBase(this);
        items = db.getallItems();
        recyclerView = findViewById(R.id.allLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,items);
        recyclerView.setAdapter(adapter);
    }

    public void displayList() {

    }

    //What happens when "add" button on MainActivity gets clicked
    public void addListClicked(View v) {
        Intent intent = new Intent (this, ToDoActivity.class);
        startActivity(intent);

    }
}
