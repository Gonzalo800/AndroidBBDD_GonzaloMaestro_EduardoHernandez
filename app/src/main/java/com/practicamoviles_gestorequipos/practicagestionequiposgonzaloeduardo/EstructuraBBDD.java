package com.practicamoviles_gestorequipos.practicagestionequiposgonzaloeduardo;

import android.provider.BaseColumns;

public final class EstructuraBBDD {
    private EstructuraBBDD() {}

    // Tabla de Equipos
    public static final String SQL_CREATE_TABLE_EQUIPOS =
            "CREATE TABLE IF NOT EXISTS " + Equipos.TABLE_NAME + " (" +
                    Equipos._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Equipos.COLUMN_NOMBRE + " TEXT NOT NULL, " +
                    Equipos.COLUMN_PUNTOS + " INTEGER DEFAULT 0);";

    public static final String SQL_DELETE_TABLE_EQUIPOS =
            "DROP TABLE IF EXISTS " + Equipos.TABLE_NAME;

    public static class Equipos implements BaseColumns {
        public static final String TABLE_NAME = "equipos";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_PUNTOS = "puntos";
    }

    // Tabla de Jugadores
    public static final String SQL_CREATE_TABLE_JUGADORES =
            "CREATE TABLE IF NOT EXISTS " + Jugadores.TABLE_NAME + " (" +
                    Jugadores._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Jugadores.COLUMN_NOMBRE + " TEXT NOT NULL, " +
                    Jugadores.COLUMN_EQUIPO_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + Jugadores.COLUMN_EQUIPO_ID + ") REFERENCES " +
                    Equipos.TABLE_NAME + "(" + Equipos._ID + "));";

    public static final String SQL_DELETE_TABLE_JUGADORES =
            "DROP TABLE IF EXISTS " + Jugadores.TABLE_NAME;

    public static class Jugadores implements BaseColumns {
        public static final String TABLE_NAME = "jugadores";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_EQUIPO_ID = "equipo_id";
    }
}
