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
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_TODO = "todo";
    private static final String KEY_TODO1 = "todo1";
    private static final String KEY_TODO2 = "todo2";
    private static final String KEY_TODO3 = "todo3";
    private static final String KEY_TODO4 = "todo4";
    private static final String KEY_TODO5 = "todo5";


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
                KEY_TODO5+" TEXT"
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
        c.put(KEY_DATE, listItem.getDate());
        c.put(KEY_TIME, listItem.getTime());
        c.put(KEY_TODO, listItem.getTodo());
        c.put(KEY_TODO1, listItem.getTodo1());
        c.put(KEY_TODO2, listItem.getTodo2());
        c.put(KEY_TODO3, listItem.getTodo3());
        c.put(KEY_TODO4, listItem.getTodo4());
        c.put(KEY_TODO5, listItem.getTodo5());

        long ID = db.insert(TABLE_NAME,null, c);
        Log.d("Inserted", "ID" + ID);
        return ID;

    }

    public ListItem getItem(long id){
        // Select all from databaseTable where id=1
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_ID, KEY_TITLE, KEY_DATE, KEY_TIME, KEY_TODO, KEY_TODO1, KEY_TODO2, KEY_TODO3,
                KEY_TODO4, KEY_TODO5},KEY_ID+"=?",
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
                    cursor.getString(9));

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
        return db.update(TABLE_NAME,c,KEY_ID+"=?",new String[]{String.valueOf(listItem.getID())});
    }

    public void deleteList(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
