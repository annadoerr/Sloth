package com.example.sloth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "AppTable";
    private static final String TABLE_NAME = "MyTable";

       public DataBase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    //column names for db
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_TODO = "todo";
    private static final String KEY_TODO1 = "todo1";
    private static final String KEY_TODO2 = "todo2";
    private static final String KEY_TODO3 = "todo3";
    private static final String KEY_TODO4 = "todo4";
    private static final String KEY_TODO5 = "todo5";
    private static final String KEY_DONE = "done";
    private static final String KEY_DONE1 = "done1";
    private static final String KEY_DONE2 = "done2";
    private static final String KEY_DONE3 = "done3";
    private static final String KEY_DONE4 = "done4";
    private static final String KEY_DONE5 = "done5";
    private static final String KEY_CHECKED_NUMBER = "checkedNumber";


    // CREATE TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE "+TABLE_NAME+" ("+
                KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_TITLE+" TEXT,"+
                KEY_DATE+" TEXT,"+
                KEY_TIME+" TEXT,"+
                KEY_TODO+" TEXT,"+
                KEY_TODO1+" TEXT,"+
                KEY_TODO2+" TEXT,"+
                KEY_TODO3+" TEXT,"+
                KEY_TODO4+" TEXT,"+
                KEY_TODO5+" TEXT, "+
                KEY_DONE+" TEXT, "+
                KEY_DONE1+" TEXT, "+
                KEY_DONE2+" TEXT, "+
                KEY_DONE3+" TEXT, "+
                KEY_DONE4+" TEXT, "+
                KEY_DONE5+" TEXT, "+
                KEY_CHECKED_NUMBER+" TEXT"
                +" )";
        db.execSQL(createDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        Log.d("TableUpgrade", "Die Tabelle mit Versionsnummer " + oldVersion + " wird entfernt.");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public long addItem(ListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE, listItem.gettitle());
        c.put(KEY_DATE, listItem.getDate());
        c.put(KEY_TIME, listItem.getTime());
        c.put(KEY_TODO, listItem.getTodo());
        c.put(KEY_TODO1, listItem.getTodo1());
        c.put(KEY_TODO2, listItem.getTodo2());
        c.put(KEY_TODO3, listItem.getTodo3());
        c.put(KEY_TODO4, listItem.getTodo4());
        c.put(KEY_TODO5, listItem.getTodo5());
        c.put(KEY_DONE, listItem.getIsDone());
        c.put(KEY_DONE1, listItem.getIsDone1());
        c.put(KEY_DONE2, listItem.getIsDone2());
        c.put(KEY_DONE3, listItem.getIsDone3());
        c.put(KEY_DONE4, listItem.getIsDone4());
        c.put(KEY_DONE5, listItem.getIsDone5());
        c.put(KEY_CHECKED_NUMBER, listItem.getCheckedNumber());

        long ID = db.insert(TABLE_NAME,null, c);
        Log.d("Inserted", "ID" + ID);
        return ID;

    }

    public ListItem getItem(long id){
        // Select all from databaseTable where id=1
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_ID, KEY_TITLE, KEY_DATE, KEY_TIME, KEY_TODO, KEY_TODO1, KEY_TODO2, KEY_TODO3,
                KEY_TODO4, KEY_TODO5, KEY_DONE, KEY_DONE1, KEY_DONE2, KEY_DONE3, KEY_DONE4, KEY_DONE5, KEY_CHECKED_NUMBER},KEY_ID+"=?",
                new String[]{String.valueOf(id)},null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

            ListItem item = new ListItem(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12),
                    cursor.getString(13),
                    cursor.getString(14),
                    cursor.getString(15),
                    cursor.getString(16));

            return item;
    }

    public List<ListItem> getallItems() {
        List<ListItem> allItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME+" ORDER BY "+KEY_ID+" DESC";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do{
                ListItem listItem = new ListItem();
                listItem.setID(cursor.getLong(0));
                listItem.setTitle(cursor.getString(1));
                listItem.setDate(cursor.getString(2));
                listItem.setTime(cursor.getString(3));
                listItem.setTodo(cursor.getString(4));
                listItem.setTodo1(cursor.getString(5));
                listItem.setTodo2(cursor.getString(6));
                listItem.setTodo3(cursor.getString(7));
                listItem.setTodo4(cursor.getString(8));
                listItem.setTodo5(cursor.getString(9));
                listItem.setIsDone(cursor.getString(10));
                listItem.setIsDone1(cursor.getString(11));
                listItem.setIsDone2(cursor.getString(12));
                listItem.setIsDone3(cursor.getString(13));
                listItem.setIsDone4(cursor.getString(14));
                listItem.setIsDone5(cursor.getString(15));
                listItem.setCheckedNumber(cursor.getString(16));


                allItems.add(listItem);

            }while(cursor.moveToNext());
        }

        return allItems;

    }

    public int editList(ListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited", "Edited Title: -> "+ listItem.gettitle() + "\n ID -> "+listItem.getID());
        c.put(KEY_TITLE,listItem.gettitle());
        c.put(KEY_DATE,listItem.getDate());
        c.put(KEY_TIME,listItem.getTime());
        c.put(KEY_TODO,listItem.getTodo());
        c.put(KEY_TODO1,listItem.getTodo1());
        c.put(KEY_TODO1,listItem.getTodo1());
        c.put(KEY_TODO2,listItem.getTodo2());
        c.put(KEY_TODO3,listItem.getTodo3());
        c.put(KEY_TODO4,listItem.getTodo4());
        c.put(KEY_TODO5,listItem.getTodo5());
        c.put(KEY_DONE, listItem.getIsDone());
        c.put(KEY_DONE1, listItem.getIsDone1());
        c.put(KEY_DONE2, listItem.getIsDone2());
        c.put(KEY_DONE3, listItem.getIsDone3());
        c.put(KEY_DONE4, listItem.getIsDone4());
        c.put(KEY_DONE5, listItem.getIsDone5());
        c.put(KEY_CHECKED_NUMBER, listItem.getCheckedNumber());

        return db.update(TABLE_NAME,c,KEY_ID+"=?",new String[]{String.valueOf(listItem.getID())});
    }

    public void deleteList(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
