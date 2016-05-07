package desmov.app.practica.com.myzoo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by solid on 04/04/2016.
 */
public class BaseHelper extends SQLiteOpenHelper
{

    String tabla = "CREATE TABLE Animales(id INTEGER PRIMARY KEY AUTOINCREMENT, Clasificacion TEXT, Nombre TEXT, Sexo TEXT, Fecha TEXT, Habitat TEXT, Alimentacion TEXT)";

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL(tabla);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
