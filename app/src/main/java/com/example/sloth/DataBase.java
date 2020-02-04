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
    private static final String TABLE_TODO_ITEM = "ToDoItem";

    public DataBase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    //column names for db
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    private static final String KEY_TODO_ID = "toDoId";
    private static final String KEY_TODO = "itemName";
    private static final String KEY_IS_CHECKED = "isChecked";

    // CREATE TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE "+TABLE_NAME+" ("+
                KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_TITLE+" TEXT,"+
                KEY_DATE+" TEXT,"+
                KEY_TIME+" TEXT"
                +" )";
        String createItemDb = "CREATE TABLE "+TABLE_TODO_ITEM+" (" +
                KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_TODO_ID+" INTEGER, "+
                KEY_TODO+" TEXT, "+
                KEY_IS_CHECKED+" INTEGER"
                +")";
        db.execSQL(createDb);
        db.execSQL(createItemDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO_ITEM);
        onCreate(db);

    }

    public long addItem(ListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE, listItem.gettitle());
        c.put(KEY_DATE, listItem.getDate());
        c.put(KEY_TIME, listItem.getTime());

        long ID = db.insert(TABLE_NAME,null, c);
        Log.d("Inserted", "ID" + ID);
        return ID;

    }

    public ListItem getItem(long id){
        // Select all from databaseTable where id=1
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_ID, KEY_TITLE, KEY_DATE, KEY_TIME },KEY_ID+"=?",
                new String[]{String.valueOf(id)},null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        ListItem item = new ListItem(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        cursor.close();
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


                allItems.add(listItem);

            }while(cursor.moveToNext());
        }

        cursor.close();
        return allItems;

    }

    public long addToDo(ToDoItem toDoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        /*c.put(KEY_TODO_ID, toDoItem.getTodo_id());*/
        c.put(KEY_TODO, toDoItem.getTodo());
        if (toDoItem.equals(1))
            c.put(KEY_IS_CHECKED, 1);
        else
            c.put(KEY_IS_CHECKED, 0);

        long ID = db.insert(TABLE_TODO_ITEM,null, c);
        Log.d("Inserted", "ToDoID" + ID);
        Log.d("Inserted", "ID" + ID);
        return ID;

    }

    public ToDoItem getTodo(long id){
        // Select all from databaseTable where id=1
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_TODO_ITEM,new String[]{KEY_ID, KEY_TODO_ID, KEY_TODO, KEY_IS_CHECKED },KEY_ID+"=?",
                new String[]{String.valueOf(id)},null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        ToDoItem toDoItem = new ToDoItem(
                cursor.getLong(0),
                cursor.getLong(1),
                cursor.getString(2),
                cursor.getInt(3)
        );

        cursor.close();
        return toDoItem;
    }

    public List<ToDoItem> getallTodos() {
        List<ToDoItem> allItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_TODO_ITEM;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do{
                ToDoItem toDoItem = new ToDoItem();
                toDoItem.setID(cursor.getLong(0));
                toDoItem.setTodo_id(cursor.getLong(1));
                toDoItem.setTodo(cursor.getString(2));
                toDoItem.setChecked(cursor.getInt(3));


                allItems.add(toDoItem);

            }while(cursor.moveToNext());
        }

        cursor.close();
        return allItems;

    }

    public int editList(ListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited", "Edited Title: -> "+ listItem.gettitle() + "\n ID -> "+listItem.getID());
        c.put(KEY_TITLE,listItem.gettitle());
        c.put(KEY_DATE,listItem.getDate());
        c.put(KEY_TIME,listItem.getTime());
        return db.update(TABLE_NAME,c,KEY_ID+"=?",new String[]{String.valueOf(listItem.getID())});
    }

    public int editTodo(ToDoItem toDoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TODO_ID,toDoItem.getTodo_id());
        c.put(KEY_TODO,toDoItem.getTodo());
        return db.update(TABLE_TODO_ITEM,c,KEY_ID+"=?",new String[]{String.valueOf(toDoItem.getID())});
    }

    public void deleteList(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.delete(TABLE_TODO_ITEM, KEY_ID+"=?", new String[] {String.valueOf(id)});
        db.close();
    }
}
