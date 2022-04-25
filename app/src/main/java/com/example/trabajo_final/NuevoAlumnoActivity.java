package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Alumnos;
import com.example.trabajo_final.Entidades.Carreras;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NuevoAlumnoActivity extends AppCompatActivity {

    EditText edtnombre, edtdni, edttelefono, edtcorreo;
    List<String> carreras = new ArrayList<String>();
    List<Integer> carrcodigo = new ArrayList<Integer>();
    Button btnagregar, btncancelar;
    Spinner spncarrera;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_alumno);
        EnlazarControles();
        CargarSpinner();

        spncarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumnos obj=new Alumnos(edtnombre.getText().toString(),
                        Integer.parseInt(edtdni.getText().toString()),
                        Integer.parseInt(edttelefono.getText().toString()),
                        edtcorreo.getText().toString(),
                        carrcodigo.get(position));

                GrabarAlumno(obj);

                Intent i= new Intent(NuevoAlumnoActivity.this, AlumnosActivity.class);

                startActivity(i);
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(NuevoAlumnoActivity.this, AlumnosActivity.class);

                startActivity(i);
            }
        });
    }

    void GrabarAlumno(Alumnos obj)
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                AppDB.getINSTANCE(NuevoAlumnoActivity.this).alumnosdao().InsertarAlumno(obj);

                //mandamos un mensaje
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //
                        Toast.makeText(NuevoAlumnoActivity.this,
                                "Alumno " + obj.getNombre() + " grabado correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //
        hilo.start();
    }

    void CargarSpinner(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                List<Carreras> lista = AppDB.getINSTANCE(NuevoAlumnoActivity.this).carrerasdao().ListarCarrerasAll();
                for (Carreras c : lista){
                    carrcodigo.add(c.getCodigo_carrera());
                    carreras.add(c.getCarrera());
                }
                spncarrera.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,carreras));
            }
        });
        //
        hilo.start();
    }

    public void EnlazarControles(){
        edtnombre= findViewById(R.id.EDTNOMBALUMNO);
        edtdni= findViewById(R.id.EDTDNIALUMNO);
        edttelefono=findViewById(R.id.EDTTELFALUMNO);
        edtcorreo=findViewById(R.id.EDTCORREOALUMNO);
        btnagregar=findViewById(R.id.BTNAGREGARALUMNO);
        btncancelar=findViewById(R.id.BTNCANCELARALUMNO2);
        spncarrera=findViewById(R.id.SPNCARR);
    }
}