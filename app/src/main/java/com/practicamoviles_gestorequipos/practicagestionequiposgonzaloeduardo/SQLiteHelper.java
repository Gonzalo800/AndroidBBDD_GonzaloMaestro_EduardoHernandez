package com.practicamoviles_gestorequipos.practicagestionequiposgonzaloeduardo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LigaDB.db";
    public static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EstructuraBBDD.SQL_CREATE_TABLE_EQUIPOS);
        db.execSQL(EstructuraBBDD.SQL_CREATE_TABLE_JUGADORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EstructuraBBDD.SQL_DELETE_TABLE_JUGADORES);
        db.execSQL(EstructuraBBDD.SQL_DELETE_TABLE_EQUIPOS);
        onCreate(db);
    }
}
