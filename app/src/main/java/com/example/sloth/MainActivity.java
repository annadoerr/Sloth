package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adds AppCompbat Toolbar to Activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //Changes AppBar Title
        getSupportActionBar().setTitle(R.string.listTitle);

        //Changes StatusBar Color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        populateItemList();

        //Time Picker



    }

    private void populateItemList() {
        // Construct the data source
        ArrayList<ListItem> arrayOfUsers = new ArrayList<ListItem>();
// Create the adapter to convert the array to views
        ListAdapter adapter = new ListAdapter(this, arrayOfUsers);
// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }

    //What happens when "add" button on MainActivity gets clicked
    public void addListClicked(View v) {
        Intent intent = new Intent (this, ToDoActivity.class);
        startActivity(intent);

    }
}
