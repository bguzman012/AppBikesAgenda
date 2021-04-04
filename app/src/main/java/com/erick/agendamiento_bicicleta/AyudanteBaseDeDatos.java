package com.erick.agendamiento_bicicleta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AyudanteBaseDeDatos extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "bicishop",
            NOMBRE_TABLA_MASCOTAS = "mascotas", NOMBRE_USUARIOS = "users", CITAS = "citas";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public AyudanteBaseDeDatos(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, cedula text, nombre text, apellido text, telefono text, direccionPrincipal text, direccionSecu text, direccionRefe text, latitud text, longitud text, correo text, password text)", NOMBRE_USUARIOS));
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, problema text, marca text, user integer, FOREIGN KEY (user) REFERENCES users(id))", CITAS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
