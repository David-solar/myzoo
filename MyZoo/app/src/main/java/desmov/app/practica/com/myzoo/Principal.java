package desmov.app.practica.com.myzoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_portal, menu);
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

            case R.id.action_eliminar:
                Intent in_el = new Intent(this, Eliminar.class);
                Toast.makeText(getApplicationContext(), "Item Eliminar", Toast.LENGTH_SHORT).show();
                startActivity(in_el);
                break;

            case R.id.action_salir:
                Toast.makeText(getApplicationContext(), "Item salir", Toast.LENGTH_SHORT).show();
                finish();
            break;


        }

        return super.onOptionsItemSelected(item);
    }

}
