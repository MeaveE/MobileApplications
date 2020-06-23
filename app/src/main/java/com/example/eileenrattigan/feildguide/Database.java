package com.example.eileenrattigan.feildguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {

    //database name
    public static final String DATABASE_NAME="FieldGuide";
    //tables
  //  public static final String TABLE_USERS = "User";
    public static final String TABLE_ANIMAL = "Animal";

    //columns users table
 //   public static final String Col_1 = "_id";
 //     public static final String Col_2 = "User_Name";
  //  public static final String Col_3 = "Password";
  //  public static final String Col_4 = "Status";
  //  public static final String Col_5 = "Notes";

    // columns animal table
    public static final String Col_id = "_id";
    public static final String Col_name = "Animal_Name";
    public static final String Col_image = "Image";
    public static final String Col_desc = "Description";
    public static final String Col_habitat = "Habitat";
    public static final String Col_life = "Lifespan";
    public static final String Col_Notes = "Notes";
    public static final String Col_Status = "Status";

    // SQL statement to create the database

   /* private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + "(" + Col_1 + " INTEGER PRIMARY KEY autoincrement," + Col_2
            + " TEXT not null," + Col_3 + " TEXT not null," + Col_4
            + " INTEGER," + Col_5 + " TEXT" + ")";

    private static final String CREATE_TABLE_ANIMAL = "CREATE TABLE "
            + TABLE_ANIMAL + "(" + Col_id + " INTEGER primary key autoincrement," + Col_name
            + " TEXT not null," + Col_image + " TEXT,"
            + Col_desc + " TEXT not null,"+ Col_habitat + " TEXT not null," + Col_life + " TEXT" + ")";
*/
   private static final String DATABASE_CREATE =
           " create table Animal ( _id Integer primary key not null,"
                   + " Animal_Name text not null," + " Image text not null," + " Description text not null," + " Habitat text not null," + " Lifespan text not null," +
                   " Notes text not null," + " Status Integer not null);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public Database(Context ctx) {

        //super(context, DATABASE_NAME, null, 1);
        this.context 	= ctx;
        DBHelper 		= new DatabaseHelper(context);
    }

    public Database open() throws SQLException
    {
        db     = DBHelper.getWritableDatabase();
        return this;
    }


    public void close()
    {

        DBHelper.close();
    }

    public long insertNotes(String Notes)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Col_Notes, Notes);
        return db.insert(TABLE_ANIMAL, null, initialValues);
    }
    public long insertStatus(Integer Status)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Col_Status, Status);
        return db.insert(TABLE_ANIMAL, null, initialValues);
    }
   /* public long insertUser( String Name, String Password)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Col_2, Name);
        initialValues.put(Col_3, Password);
        return db.insert(TABLE_USERS, null, initialValues);
    }*/
    public long insertAnimals( String Name, String Image, String Description, String Habitat, String Lifespan)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Col_name, Name);
        initialValues.put(Col_image,Image);
        initialValues.put(Col_desc, Description);
        initialValues.put(Col_habitat,Habitat);
        initialValues.put(Col_life,Lifespan);
        return db.insert(TABLE_ANIMAL, null, initialValues);
    }

//adust

  /*  public  Cursor login(String username, String password){

        return db.query( TABLE_USERS, new String[]{
                    Col_2,
                    Col_3
                } ,  Col_2 + " LIKE " + username + " and " + Col_3 + " LIKE " + password ,null,null,null,null);

    }
*/
    public Cursor status(){
        return db.query( TABLE_ANIMAL, new String[]{
                Col_Status
        } , null, null,null,null,null);

    }

    public Cursor getAllAnimals()
    {
            return db.query( TABLE_ANIMAL, new String[]
                                {
                                        Col_image,
                                        Col_name
                                },
                        null,  null, null, null, null, null);
    }


    public boolean updateNotes(long rowId, String Notes, Integer Status)
    {
        ContentValues args = new ContentValues();
        args.put(Col_Notes, Notes);
        args.put(Col_Status, Status);
        return db.update(TABLE_ANIMAL, args,
                Col_id +"="+ rowId, null) > 0;
    }

    ///////////////////nested dB helper class ///////////////////////////////////////
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

           // db.execSQL(CREATE_TABLE_USERS);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        //
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
            // on upgrade drop older tables
          //  db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMAL);

            // create new tables
            onCreate(db);
        }
    }
}
