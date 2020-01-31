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

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "DataTable";
        private static final String TABLE_NAME = "ItemsTable";

       public DataBase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    //column names for db
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    // CREATE TABLE nametable(id INT PRIMARY KEY, title TEXT, content TEXT, date TEXT, time TEXT);
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE "+TABLE_NAME+" ("+
                KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_TITLE+" TEXT,"+
                KEY_CONTENT+" TEXT,"+
                KEY_DATE+" TEXT,"+
                KEY_TIME+" TEXT"
                +" )";
        db.execSQL(createDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public long addItem(ListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE, listItem.gettitle());
        c.put(KEY_CONTENT, listItem.getContent());
        c.put(KEY_DATE, listItem.getDate());
        c.put(KEY_TIME, listItem.getTime());

        long ID = db.insert(TABLE_NAME,null, c);
        Log.d("Inserted", "ID" + ID);
        return ID;

    }

    public ListItem getItem(long id){
        // Select all from databaseTable where id=1
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE, KEY_TIME },KEY_ID+"=?",
                new String[]{String.valueOf(id)},null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

            ListItem item = new ListItem(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

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
                listItem.setContent(cursor.getString(2));
                listItem.setDate(cursor.getString(3));
                listItem.setTime(cursor.getString(4));


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
        c.put(KEY_CONTENT,listItem.getContent());
        c.put(KEY_DATE,listItem.getDate());
        c.put(KEY_TIME,listItem.getTime());
        return db.update(TABLE_NAME,c,KEY_ID+"=?",new String[]{String.valueOf(listItem.getID())});
    }

    public void deleteList(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
