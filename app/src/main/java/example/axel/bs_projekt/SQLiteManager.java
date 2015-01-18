package example.axel.bs_projekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Axel on 17.10.2014.
 */
public class SQLiteManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "game.sqlite";
    public static final String TABLE_USER = "t_user";
    public static final String TABLE_PUNKTE = "t_punkte";
    public static final String ID = "_id";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PUNKTE = "punkte";
    public static final String ID_NAME = "id_name";
    public static final String DATUM = "datum";
    public static final String IMAGE = "image";


    Singleton singleton;


    public SQLiteManager (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_USER + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, password TEXT, email TEXT, image BLOB)");
        db.execSQL("CREATE TABLE " + TABLE_PUNKTE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, id_name INTEGER, punkte INTEGER, datum DATETIME DEFAULT CURRENT_TIMESTAMP)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        android.util.Log.v("Constants", "Upgrading database, which will destroy all old data");
        db.execSQL("DROP TABLE IF EXIST " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PUNKTE);
        onCreate(db);

    }

    public int updateProcess(SQLiteDatabase db, int id, String password, String email) {

        singleton = Singleton.GetInstance();

        ContentValues args = new ContentValues();
        args.put(PASSWORD, password);
        args.put(EMAIL, email);
        return db.update(TABLE_USER, args, ID + " = ?", new String[] { String.valueOf(id)});

    }

    public int saveImageProcess(SQLiteDatabase db, int id, byte[] image) {

        singleton = Singleton.GetInstance();

        ContentValues args = new ContentValues();
        args.put(IMAGE, String.valueOf(image));
        return db.update(TABLE_USER, args, ID + " = ?", new String[] { String.valueOf(id)});

    }


}
