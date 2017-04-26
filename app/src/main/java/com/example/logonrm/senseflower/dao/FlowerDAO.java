package com.example.logonrm.senseflower.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.logonrm.senseflower.model.Flower;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 01/04/2017.
 */

public class FlowerDAO {

    private static final String DATABASE_NAME = "flowerdb.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "TBFLOWER";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + TABLE_NAME + " (tipo) values (?)";

    public FlowerDAO (Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    public long insert(Flower flower) {
        this.insertStmt.bindLong(1, flower.getTipo());
        return this.insertStmt.executeInsert();
    }

    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    public List<Flower> selectAll() {
        List<Flower> list = new ArrayList<Flower>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[] { "id", "tipo" },
                null, null, null, null, "id");

        if (cursor.moveToFirst()) {
            do {
                Flower flower = new Flower();
                flower.setId  (cursor.getInt(0));
                flower.setTipo(cursor.getLong(1));
                list.add(flower);

            } while (cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }


        return list;


    }

    public Flower selectById(int id) {
        Flower flower = new Flower();
        Cursor cursor = this.db.query(TABLE_NAME, new String[] { "id", "tipo" },
                "id="+id, null, null, null, "id");

        if (cursor.moveToFirst()) {
        do{
                flower.setId  (cursor.getInt(0));
                flower.setTipo(cursor.getLong(1));

            } while (cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }


        return flower;


    }

    public void encerrarDB() {
        this.db.close();
    }

    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT)");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Example", "*** Upgrading database, this will drop tables and recreate.");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
