package desmov.app.practica.com.myzoo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaActionSound;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Objects;

public class Eliminar extends AppCompatActivity {

    String[] info;
    ListView lis;

    protected ActionMode mActionMode;
    protected int selectedIt = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mostrar();
        lis = (ListView) findViewById(R.id.lve_e);
        lis.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        lis.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(mActionMode != null)
                {
                    return false;
                }
                selectedIt = position;

                mActionMode = Eliminar.this.startActionMode(amc);
                view.setSelected(true);
                return true;

            }
        });

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

            Cursor c = db.rawQuery("select * from Animales", null);
            int cantidad = c.getCount();

            info = new String[cantidad];
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
            ListView lista = (ListView) findViewById(R.id.lve_e);

            lista.setAdapter(adaptador);

        }

    }

    private ActionMode.Callback amc = new ActionMode.Callback()
    {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu)
        {

            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_eliminar, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item)
        {

            switch (item.getItemId())
            {
                case R.id.action_borrar:
                    Borrar();
                    mode.finish();
                    return true;


                default:
                    return false;

            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode)
        {

            mActionMode = null;
            selectedIt = -1;

        }
    };

    private void Borrar()
    {
        BaseHelper bh = new BaseHelper(this, "BaseDatos",null,1);
        SQLiteDatabase db = bh.getWritableDatabase();

        Cursor c1 = db.rawQuery("select id from Animales", null);

        int id = 0;
        if(c1.moveToFirst())
        {
            for(int x = 0; x < (selectedIt+1); x++)
            {
                id = c1.getInt(0);
                c1.moveToNext();
            }
        }


        /*if (c1.moveToFirst())
        {
            //Recorremos el cursor hasta que no haya más registros
            do
            {
                id = c1.getInt(0);
            } while(c1.moveToNext());
        }*/



        if(db != null)
        {
            long res = db.delete("Animales", "id=" + id, null);

            if(res > 0)
            {
                Toast.makeText(this, "Registro Eliminado", Toast.LENGTH_SHORT).show();
                mostrar();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eliminar2, menu);
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

            case R.id.action_crearEspecie:
                Intent in_ce = new Intent(this,CrearEspecie.class);
                Toast.makeText(getApplicationContext(), "Item Crear Especie", Toast.LENGTH_SHORT).show();
                startActivity(in_ce);
                break;

            case R.id.action_listar:
                Intent in_l = new Intent(this, Listar.class);
                Toast.makeText(getApplicationContext(), "Item Listar Especies", Toast.LENGTH_SHORT).show();
                startActivity(in_l);
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
