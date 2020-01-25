package com.example.sloth;

public class ListItem {

    private long ID;
    private String deadline;
    private String date;
    private String time;
    private String title;
    private String content;
    private String leafs;
    private String number;


    //Instance of ListItem
    ListItem(String string, String toString, String s) {}

    //ListItem without ID
    ListItem(String deadline, String date, String time, String title, String content, String leafs, String leafNumber) {
        this.deadline = deadline;
        this.date = date;
        this.time = time;
        this.title = title;
        this.content = content;
        this. leafs = leafs;
        this.number = leafNumber;
    }
    //ListItem with ID
    ListItem(long id, String deadline, String date, String time, String title, String content, String leafs, String leafNumber) {
        this.ID = id;
        this.deadline = deadline;
        this.date = date;
        this.time = time;
        this.title = title;
        this.content = content;
        this. leafs = leafs;
        this.number = leafNumber;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
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

    public String gettitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLeafs() {
        return leafs;
    }

    public void setLeafs(String leafs) {
        this.leafs = leafs;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
