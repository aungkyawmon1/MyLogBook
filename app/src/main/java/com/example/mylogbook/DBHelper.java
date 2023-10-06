package com.example.mylogbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PhoneContacts.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_DATE_OF_BIRTH = "dateOfBirth";
    public static final String CONTACTS_COLUMN_PHOTO = "photo";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE " + CONTACTS_TABLE_NAME + " ("
                        + CONTACTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + CONTACTS_COLUMN_NAME + " TEXT,"
                        + CONTACTS_COLUMN_EMAIL + " TEXT,"
                        + CONTACTS_COLUMN_PHOTO + " TEXT,"
                        + CONTACTS_COLUMN_DATE_OF_BIRTH +" TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact (String name, String email, int photo, String dateOfBirth) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, name);
        contentValues.put(CONTACTS_COLUMN_EMAIL, email);
        contentValues.put(CONTACTS_COLUMN_DATE_OF_BIRTH, dateOfBirth);
        contentValues.put(CONTACTS_COLUMN_PHOTO, photo);
        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CONTACTS_TABLE_NAME ,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<ContactModel> getAllContacts() {
        ArrayList<ContactModel> array_list = new ArrayList<ContactModel>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + CONTACTS_TABLE_NAME + " ORDER BY "+ CONTACTS_COLUMN_NAME +" COLLATE NOCASE ASC", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add( new ContactModel(
                    res.getInt(0),
                    res.getString(1),
                    res.getString(2),
                    res.getInt(3),
                    res.getString(4)
                    ));
            res.moveToNext();
        }
        return array_list;
    }
}