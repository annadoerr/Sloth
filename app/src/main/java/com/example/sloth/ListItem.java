package com.example.sloth;

public class ListItem {

    private long ID;
    private String title;
    private String content;
    private String date;
    private String time;



    //ListItem without ID
    ListItem(String title, String content, String date, String time) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;

    }
    //ListItem with ID
    ListItem(long id, String title, String content, String date, String time) {
        this.ID = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;

    }

    //Instance of ListItem
    ListItem() {}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String gettitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

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


}
