package desmov.app.practica.com.myzoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button acep, can;

    EditText nom, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        nom = (EditText) findViewById(R.id.etm_usuario);
        pass = (EditText) findViewById(R.id.etm_contra);

        acep = (Button) findViewById(R.id.m_aceptar);
        can = (Button) findViewById(R.id.m_cancelar);

        acep.setOnClickListener(this);
        can.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.m_aceptar:

                String n = nom.getText().toString();
                String p = pass.getText().toString();
                int cont = 0;

                if(n.compareToIgnoreCase("david") == 0)
                {
                    cont = 1;
                    if(p.compareToIgnoreCase("movil16a2p") == 0)
                    {
                        cont = 2;
                        Toast.makeText(getApplicationContext(),"Datos Correctos", Toast.LENGTH_SHORT).show();
                        Intent in_acceso = new Intent(MainActivity.this,Principal.class);
                        startActivity(in_acceso);
                    }

                }

                if(cont == 0)
                {
                    Toast.makeText(getApplicationContext(),"Nombre incorrecto", Toast.LENGTH_SHORT).show();
                }
                else if(cont == 1)
                {
                    Toast.makeText(getApplicationContext(),"Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }



                break;

            case R.id.m_cancelar:

                Toast.makeText(getApplicationContext(),"Gracias por abrir la aplicacion", Toast.LENGTH_SHORT).show();
                System.exit(0);

                break;
        }
    }
}
