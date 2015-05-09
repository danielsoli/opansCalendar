package no.dansol.opansCalendar.SQLController;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import no.dansol.opansCalendar.CalendarFragment;
import no.dansol.opansCalendar.DBhelper.DBhelper;

import java.io.DataOutput;

/**
 * Created by dansol on 09.05.2015.
 */
public class SQLController {

    private DBhelper dbHelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        ourcontext = c;
    }

    public SQLController open() throws SQLException {
        dbHelper = new DBhelper(ourcontext);
        database = dbHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String date, String time, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBhelper.EVENT_NAME, name);
        contentValue.put(DBhelper.EVENT_DATE, date);
        contentValue.put(DBhelper.EVENT_TIME, time);
        contentValue.put(DBhelper.EVENT_DESC, desc);
        database.insert(DBhelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBhelper._ID, DBhelper.EVENT_NAME,
                DBhelper.EVENT_DATE, DBhelper.EVENT_TIME, DBhelper.EVENT_DESC };
        Cursor cursor = database.query(DBhelper.TABLE_NAME, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String date, String time, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.EVENT_NAME, name);
        contentValues.put(DBhelper.EVENT_DATE, date);
        contentValues.put(DBhelper.EVENT_TIME, time);
        contentValues.put(DBhelper.EVENT_DESC, desc);
        int i = database.update(DBhelper.TABLE_NAME, contentValues,
                DBhelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DBhelper.TABLE_NAME, DBhelper._ID + "=" + _id, null);
    }

}
