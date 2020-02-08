package com.example.sloth;

public class ListItem {

    private long ID;
    private String title;
    private String date;
    private String time;
    private String todo;
    private String todo1;
    private String todo2;
    private String todo3;
    private String todo4;
    private String todo5;
    private String isDone;
    private String isDone1;
    private String isDone2;
    private String isDone3;
    private String isDone4;
    private String isDone5;
    private String checkedNumber;




    //ListItem without ID
    ListItem(String title, String date, String time, String todo, String todo1, String todo2, String todo3, String todo4, String todo5,
             String isDone, String isDone1, String isDone2, String isDone3, String isDone4, String isDone5, String checkedNumber ) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.todo = todo;
        this.todo1 = todo1;
        this.todo2 = todo2;
        this.todo3 = todo3;
        this.todo4 = todo4;
        this.todo5 = todo5;
        this.isDone = isDone;
        this.isDone1 = isDone1;
        this.isDone2 = isDone2;
        this.isDone3 = isDone3;
        this.isDone3 = isDone3;
        this.isDone4 = isDone4;
        this.isDone5 = isDone5;
        this.checkedNumber = checkedNumber;

    }
    //ListItem with ID
    ListItem(long id, String title, String date, String time, String todo, String todo1, String todo2, String todo3, String todo4, String todo5,
             String isDone, String isDone1, String isDone2,String isDone3, String isDone4, String isDone5, String checkedNumber) {
        this.ID = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.todo = todo;
        this.todo1 = todo1;
        this.todo2 = todo2;
        this.todo3 = todo3;
        this.todo4 = todo4;
        this.todo5 = todo5;
        this.isDone = isDone;
        this.isDone1 = isDone1;
        this.isDone2 = isDone2;
        this.isDone3 = isDone3;
        this.isDone3 = isDone3;
        this.isDone4 = isDone4;
        this.isDone5 = isDone5;
        this.checkedNumber = checkedNumber;
    }

    //Instance of ListItem
    ListItem() {}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String gettitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getTodo1() {
        return todo1;
    }

    public void setTodo1(String todo1) {
        this.todo1 = todo1;
    }

    public String getTodo2() {
        return todo2;
    }

    public void setTodo2(String todo2) {
        this.todo2 = todo2;
    }

    public String getTodo3() {
        return todo3;
    }

    public void setTodo3(String todo3) {
        this.todo3 = todo3;
    }

    public String getTodo4() {
        return todo4;
    }

    public void setTodo4(String todo4) {
        this.todo4 = todo4;
    }

    public String getTodo5() {
        return todo5;
    }

    public void setTodo5(String todo5) {
        this.todo5 = todo5;
    }

    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }

    public String getIsDone1() {
        return isDone1;
    }

    public void setIsDone1(String isDone1) {
        this.isDone1 = isDone1;
    }

    public String getIsDone2() {
        return isDone2;
    }

    public void setIsDone2(String isDone2) {
        this.isDone2 = isDone2;
    }

    public String getIsDone3() {
        return isDone3;
    }

    public void setIsDone3(String isDone3) {
        this.isDone3 = isDone3;
    }

    public String getIsDone4() {
        return isDone4;
    }

    public void setIsDone4(String isDone4) {
        this.isDone4 = isDone4;
    }

    public String getIsDone5() {
        return isDone5;
    }

    public void setIsDone5(String isDone5) {
        this.isDone5 = isDone5;
    }

    public String getCheckedNumber() {
        return checkedNumber;
    }

    public void setCheckedNumber(String checkedNumber) {
        this.checkedNumber = checkedNumber;
    }
}
