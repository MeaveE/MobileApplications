package com.example.eileenrattigan.feildguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//error  Caused by: java.lang.IllegalArgumentException: column '_id' does not exist
//it does exist i downloaded the database and it was there
public class DatabaseAnimal {

    public static final String DATABASE_NAME = "Database1";
    public static final String TABLE_NAME = "Creature";
    public static String Col_ID = "_id";
    public static String NAME = "name";
    public static String IMAGE = "image";
    public static String DESC = "description";
    public static String HABITAT = "habitat";
    public static String LIFESPAN = "lifespan";
    public static String STATUS = "status";


    private static final String DATABASE_CREATE =
            "create table Creature ( _id integer primary key autoincrement, " +
                    "name text not null, " + "image Text, " + "description text not null, " + "habitat text not null, " + "lifespan text not null, " +
                    "status Integer not null default 0);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public DatabaseAnimal(Context ctx) {

        //super(context, DATABASE_NAME, null, 1);
        this.context 	= ctx;
        DBHelper 		= new DatabaseHelper(context);
    }

    public DatabaseAnimal open() throws SQLException
    {
        db     = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {

        DBHelper.close();
    }

    public long insertAnimals( String Name, String Image, String Description, String Habitat, String Lifespan)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NAME, Name);
        initialValues.put(IMAGE,Image);

        initialValues.put(DESC, Description);
        initialValues.put(HABITAT,Habitat);
        initialValues.put(LIFESPAN,Lifespan);
        return db.insert(TABLE_NAME, null, initialValues);
    }

    public Cursor getAllAnimals()
    {
        return db.query(TABLE_NAME, new String[]
                        {
                                Col_ID,
                                IMAGE,
                                NAME,
                                STATUS

                        },
                null, null, null, null, null);
    }

    public Cursor getAnimal()
    {
        return db.query(TABLE_NAME, new String[]
                        {
                                Col_ID,
                                IMAGE,
                                NAME,
                                DESC,
                                HABITAT,
                                LIFESPAN

                        },
                null, null, null, null, null);
    }

    public Cursor status(){
        return db.query( TABLE_NAME, new String[]{
                STATUS
        } , null, null,null,null,null);

    }
    public Cursor getSearch(String Search) throws SQLException
    {
        Cursor mCursor =
                db.query(true, TABLE_NAME, new String[]
                                {
                                        Col_ID,
                                        IMAGE,
                                        NAME
                                },
                         NAME + "=" + Search,  null, null, null, null, null);

        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public long insertStatus(Integer Status)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(STATUS, Status);
        return db.insert(TABLE_NAME, null, initialValues);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        //
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, 1);
        }


        @Override
        //
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        //
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
            // dB structure change..
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);

        }
    }
}
