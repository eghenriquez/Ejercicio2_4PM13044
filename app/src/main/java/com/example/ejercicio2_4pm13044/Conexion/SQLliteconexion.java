package com.example.ejercicio2_4pm13044.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ejercicio2_4pm13044.Operaciones.Transacciones;

public class SQLliteconexion extends SQLiteOpenHelper
{
    Context context;
    public SQLliteconexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, dbname, factory, version);
    }

    public SQLliteconexion(Context context) {
        super(context, Transacciones.NameDatabase, null, Transacciones.versionDatabase);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Transacciones.CreateTableSignature);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(Transacciones.DropTableSignature);
        onCreate(db);
    }
}
