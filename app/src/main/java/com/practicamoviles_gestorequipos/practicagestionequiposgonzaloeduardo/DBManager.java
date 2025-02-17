package com.practicamoviles_gestorequipos.practicagestionequiposgonzaloeduardo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new SQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Insertar equipo
    public long insertarEquipo(String nombre) {
        ContentValues values = new ContentValues();
        values.put(EstructuraBBDD.Equipos.COLUMN_NOMBRE, nombre);
        return db.insert(EstructuraBBDD.Equipos.TABLE_NAME, null, values);
    }

    public void modificarEquipo(String nombre, int puntos) {
        ContentValues values = new ContentValues();
        values.put(EstructuraBBDD.Equipos.COLUMN_PUNTOS, puntos);
        db.update(EstructuraBBDD.Equipos.TABLE_NAME, values, "NOMBRE = ?", new String[]{nombre});
    }

    // Obtener todos los equipos
    public List<Equipo> obtenerEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        Cursor cursor = db.query(EstructuraBBDD.Equipos.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            equipos.add(new Equipo(
                    cursor.getInt(cursor.getColumnIndexOrThrow(EstructuraBBDD.Equipos._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EstructuraBBDD.Equipos.COLUMN_NOMBRE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(EstructuraBBDD.Equipos.COLUMN_PUNTOS))
            ));
        }
        cursor.close();
        return equipos;
    }

    // Insertar jugador
    public long insertarJugador(String nombre, int equipoId) {
        ContentValues values = new ContentValues();
        values.put(EstructuraBBDD.Jugadores.COLUMN_NOMBRE, nombre);
        values.put(EstructuraBBDD.Jugadores.COLUMN_EQUIPO_ID, equipoId);
        return db.insert(EstructuraBBDD.Jugadores.TABLE_NAME, null, values);
    }

    public void eliminarEquipo(String nombre){
        db.execSQL("DELETE FROM EQUIPOS WHERE NOMBRE = '" + nombre + "'");

    }
    public void eliminarJugador(String nombre){
        db.execSQL("DELETE FROM JUGADORES WHERE NOMBRE = '" + nombre + "'");
    }
    public List<Jugador> obtenerJugadoresPorEquipo(int equipoId) {
        List<Jugador> jugadores = new ArrayList<>();
        String selection = EstructuraBBDD.Jugadores.COLUMN_EQUIPO_ID + " = ?";
        String[] selectionArgs = {String.valueOf(equipoId)};
        Cursor cursor = db.query(EstructuraBBDD.Jugadores.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            jugadores.add(new Jugador(
                    cursor.getInt(cursor.getColumnIndexOrThrow(EstructuraBBDD.Jugadores._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EstructuraBBDD.Jugadores.COLUMN_NOMBRE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(EstructuraBBDD.Jugadores.COLUMN_EQUIPO_ID))
            ));
        }
        cursor.close();
        return jugadores;
    }

    public void cerrar() {
        db.close();
    }
}
