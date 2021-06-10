package com.example.task9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.COL1 + " TEXT, "
                + Util.COL2 + " REAL, "
                + Util.COL3 + " REAL" + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }


    public long insertLocation(Location location)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.COL1, location.getName());
        contentValues.put(Util.COL2, location.getLatitude());
        contentValues.put(Util.COL3, location.getLongitude());

        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);

        db.close();

        return newRowId;
    }

    public List<Location> GetAllLocations()
    {
        List<Location> locationList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selectAllLocations = "SELECT * FROM " + Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAllLocations, null);

        if (cursor.moveToFirst())
        {
            do {
                Location location = new Location();

                location.setName(cursor.getString(cursor.getColumnIndex(Util.COL1)));
                location.setLatitude(cursor.getDouble(cursor.getColumnIndex(Util.COL2)));
                location.setLongitude(cursor.getDouble(cursor.getColumnIndex(Util.COL3)));

                locationList.add(location);

            } while (cursor.moveToNext());
        }

        db.close();;

        return locationList;
    }
}
