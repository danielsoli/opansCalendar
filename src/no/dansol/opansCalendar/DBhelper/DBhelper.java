package no.dansol.opansCalendar.DBhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dansol on 09.05.2015.
 */
public class DBhelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "EVENTS";

    // Table Columns
    public static final String _ID = "id";
    public static final String EVENT_NAME = "name"; // Type event, O1, O2, D4, Ferie osv..
    public static final String EVENT_DATE = "dato"; // Dato for eventen. En dato kan ha flere eventer
    public static final String EVENT_TIME = "time"; //Tidspunkt
    public static final String EVENT_DESC = "description"; // mer info, f.eks ved overtid

    // Database Information
    static final String DB_NAME = "OPANSKALENDER_EVENTS.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EVENT_NAME + " TEXT NOT NULL, " + EVENT_DATE + " TEXT NOT NULL, "
            + EVENT_TIME + " TEXT NOT NULL, " + EVENT_DESC + " TEXT);";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
