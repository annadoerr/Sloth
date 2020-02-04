package com.example.sloth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ToDoItem extends AppCompatActivity {

    private long ID;
    private long todo_id;
    private String todo;
    private int checked;


    //ListItem without ID
    ToDoItem(String todo) {
        this.todo_id = todo_id;
        this.todo = todo;
        this.checked = checked;

    }
    //ListItem with ID
    ToDoItem(long id, long todo_id, String todo, int checked) {
        this.ID = id;
        this.todo_id = todo_id;
        this.todo = todo;
        this.checked = checked;
    }

    //Instance of ListItem
    ToDoItem() {}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(long todo_id) {
        this.todo_id = todo_id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }
}
