package desmov.app.practica.com.myzoo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Listar extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mostrar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    public void mostrar()
    {
        BaseHelper bh = new BaseHelper(this, "BaseDatos",null,1);
        SQLiteDatabase db = bh.getWritableDatabase();

        if(db != null)
        {

            Cursor c = db.rawQuery("select * from Animales",null);
            int cantidad = c.getCount();

            String[] info = new String[cantidad];
            int i = 0;

            if(c.moveToFirst())
            {
                do
                {
                    String linea =
                            "Id: " + c.getInt(0) + "\n" +
                                    "Clasificacion: " + c.getString(1) + "\n" +
                                    "Nombre: " + c.getString(2) + "\n" +
                                    "Sexo: " + c.getString(3) + "\n" +
                                    "Fecha de Ingreso: " + c.getString(4) + "\n" +
                                    "Habitat: " + c.getString(5) + "\n" +
                                    "Alimentacion: " + c.getString(6);
                    info[i] = linea;
                    i++;

                }while(c.moveToNext());
            }

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, info);
            ListView lista = (ListView) findViewById(R.id.lv);

            lista.setAdapter(adaptador);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id)
        {
            case R.id.action_acercade:
                Intent in_acerca = new Intent(this,AcercaDe.class);
                Toast.makeText(getApplicationContext(), "Item AcercaDe", Toast.LENGTH_SHORT).show();
                startActivity(in_acerca);
                break;

            case R.id.action_eliminar:
                Intent in_el = new Intent(this, Eliminar.class);
                Toast.makeText(getApplicationContext(), "Item Eliminar", Toast.LENGTH_SHORT).show();
                startActivity(in_el);
                break;

            case R.id.action_crearEspecie:
                Intent in_ce = new Intent(this,CrearEspecie.class);
                Toast.makeText(getApplicationContext(), "Item Crear Especie", Toast.LENGTH_SHORT).show();
                startActivity(in_ce);
                break;

            case R.id.action_actualizar:
                Intent in_a = new Intent(this, Listar.class);
                Toast.makeText(getApplicationContext(), "Item Actualizar", Toast.LENGTH_SHORT).show();
                startActivity(in_a);
                break;

            case R.id.action_salir:
                Toast.makeText(getApplicationContext(), "Item salir", Toast.LENGTH_SHORT).show();
                System.exit(0);
                break;


        }

        return super.onOptionsItemSelected(item);
    }

}
