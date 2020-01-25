package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
    }

    public String deadline;
    public int date;
    public int time;
    public String title;
    public String leafs;
    public int number;

    public ListItem(String deadline, int date, int time, String title, String leafs, int leafNumber) {
        this.deadline = deadline;
        this.date = date;
        this.time = time;
        this.title = title;
        this. leafs = leafs;
        this.number = leafNumber;

    }
}
