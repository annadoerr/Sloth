package com.example.sloth;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "DataTable";
        private static final String TABLE_NAME = "NotesTable";

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

    public long addNote(ListItem listItem) {
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
}
