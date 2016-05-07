package desmov.app.practica.com.myzoo;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.Calendar;
import java.util.List;


public class CrearEspecie extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner cals, nom;

    Button bot;

    EditText fec;
    EditText hab;
    EditText alim;

    String sexo;

    CheckBox cb;

    Button save;

    private int mYear;
    private int mMonth;
    private int mDay;


    /*private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    fec.setText(((mDay < 10) ? "0" + mDay : mDay) + "/" + ((mMonth < 10) ? "0" + mMonth : mMonth) + "/" + mYear);
                }
            };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_especie);
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

        bot = (Button) findViewById(R.id.btnce_enlistar);

        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamada();
            }
        });

        hab = (EditText) findViewById(R.id.etce_habitat);
        alim = (EditText) findViewById(R.id.etce_alimentacion);

        cb = (CheckBox) findViewById(R.id.cbce_sexo);

        fec = (EditText) findViewById(R.id.etce_fecha);

        cals = (Spinner) findViewById(R.id.sce_clasificacion);
        nom = (Spinner) findViewById(R.id.sce_nombre);

        String []clasificacion={"reptiles","aereos","acuaticos","terrestres"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, clasificacion);
        cals.setAdapter(adapter);

        cals.setOnItemSelectedListener(this);


        if(cb.isChecked())
        {
            sexo = "masculino";
        }
        else
        {
            sexo = "femenino";
        }

       /*
        b.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                           switch (v.getId())
                           {
                               case R.id.btnce_guardar:
                                   /*int radioButtonID = radioGroup.getCheckedRadioButtonId();
                                   View radioButton = radioGroup.findViewById( radioButtonID );
                                   int index = radioGroup.indexOfChild( radioButton );*/
                                   /*
                                   String sexo;

                                   if(cb.isChecked())
                                   {
                                       sexo = "masculino";
                                   }
                                   else
                                   {
                                       sexo = "femenino";
                                   }
                                   //Registra en la base de datos
                                   if ( sqlite.addRegistro(
                                           cals.getSelectedItem().toString(),
                                           nom.getSelectedItem().toString(),
                                           sexo,
                                           txtFechaNac.getText().toString(),
                                           hab.getText().toString(),
                                           alim.getText().toString()) )
                                   {
                                       //recupera ID de ultimo registro y pasa como parametro
                                       int id = sqlite.getUltimoID();
                                       Bundle bundle = new Bundle();
                                       bundle.putInt("id", id);
                                       Intent intent = new Intent( CrearEspecie.this, Listar.class );
                                       intent.putExtras( bundle );
                                       startActivity( intent );
                                   }
                                   else
                                   {
                                       Toast.makeText(getBaseContext(), "Error: Compruebe que los datos sean correctos"  ,Toast.LENGTH_SHORT).show();
                                   }
                                   break;
                           }


                    }
                }
        );
*/
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        //muestra la fecha de la forma 00/00/0000

        String d = String.valueOf((mDay < 10) ? "0" + mDay : mDay) + "/";
        String m = String.valueOf((mMonth < 10) ? "0" + mMonth : mMonth) + "/";
        String a = String.valueOf(mYear);

        fec.setText(d + m + a);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String []reptiles={"serpiente","iguana","cocodrilo","tortuga"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, reptiles);

        String []aereos={"Aguila","Pato","Flamingo","Buho"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, aereos);

        String []acuaticos={"Balleno","Tiburon","Delfin","Peces"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, acuaticos);

        String []terrestres={"Tigre","Elefante","Cebra","Jirafa"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, terrestres);

        switch (position)

        {
            case 0:
                nom.setAdapter(adapter1);
                break;

            case 1:
                nom.setAdapter(adapter2);
                break;

            case 2:
                nom.setAdapter(adapter3);
                break;

            case 3:
                nom.setAdapter(adapter4);
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crearespecie, menu);
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

            case R.id.action_listar:
                Intent in_li = new Intent(this, Listar.class);
                Toast.makeText(getApplicationContext(), "Item Listar", Toast.LENGTH_SHORT).show();
                startActivity(in_li);
                break;

            case R.id.action_actualizar:
                Intent in_ac = new Intent(this, Listar.class);
                Toast.makeText(getApplicationContext(), "Item Actualizar", Toast.LENGTH_SHORT).show();
                startActivity(in_ac);
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

    public void guardar(View v)
    {
        //System.out.println("---------------------------------------------------");
        String clasificacion = cals.getSelectedItem().toString();
        //System.out.println(clasificacion);
        String nombre = nom.getSelectedItem().toString();
        //System.out.println(nombre);
        //System.out.println(sexo);
        //sexo ya esta
        String fechaIn = fec.getText().toString();
        System.out.println(fechaIn);
        String habit = hab.getText().toString();
        String alimenta = alim.getText().toString();

        //System.out.println(clasificacion + nombre + sexo + fechaIn + habit + alimenta + "++++++++++++++++++++++++++++++++++++++++++++");

        BaseHelper bh = new BaseHelper(this, "BaseDatos",null,1);
        SQLiteDatabase db = bh.getWritableDatabase();

        if(db != null)
        {
            ContentValues registroNuevo = new ContentValues();

            registroNuevo.put("Clasificacion", clasificacion);
            registroNuevo.put("Nombre", nombre);
            registroNuevo.put("Sexo", sexo);
            registroNuevo.put("Fecha", fechaIn);
            registroNuevo.put("Habitat", habit);
            registroNuevo.put("Alimentacion", alimenta);

            long i = db.insert("Animales", null, registroNuevo);

            if(i > 0)
            {
                cb.setChecked(false);
                hab.setText("");
                alim.setText("");
                Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void llamada()
    {
        Intent in = new Intent(this, Listar.class);
        startActivity(in);
    }

}
