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
import com.example.trabajo_final.Entidades.QueryAlumnos;

import java.util.ArrayList;
import java.util.List;

public class EditarAlumnoActivity extends AppCompatActivity {

    EditText edtnombre, edtdni, edttelefono, edtcorreo;
    List<String> carreras = new ArrayList<String>();
    List<Integer> carrcodigo = new ArrayList<Integer>();
    Spinner spncarrera;
    int position = 0;
    Button btnedita, btncancelar;
    QueryAlumnos obj;
    int codigoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alumno);
        codigoSeleccionado = getIntent().getIntExtra("codigo",0);
        EnlazarControles();
        CargarSpinner();
        CargarAlumno();

        spncarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnedita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumnos obj=new Alumnos(codigoSeleccionado,
                        edtnombre.getText().toString(),
                        Integer.parseInt(edtdni.getText().toString()),
                        Integer.parseInt(edttelefono.getText().toString()),
                        edtcorreo.getText().toString(),
                        carrcodigo.get(position));

                GrabarAlumno(obj);

                Intent i= new Intent(EditarAlumnoActivity.this, AlumnosActivity.class);

                startActivity(i);
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(EditarAlumnoActivity.this, AlumnosActivity.class);

                startActivity(i);
            }
        });
    }

    void CargarSpinner(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                List<Carreras> lista = AppDB.getINSTANCE(EditarAlumnoActivity.this).carrerasdao().ListarCarrerasAll();
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

    void CargarAlumno(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                obj = AppDB.getINSTANCE(EditarAlumnoActivity.this).alumnosdao().TraerAlumno(codigoSeleccionado);
                edtnombre.setText(obj.getNombre());
                edtdni.setText(obj.getDni()+"");
                edttelefono.setText(obj.getTelefono()+"");
                edtcorreo.setText(obj.getCorreo());
                position = carreras.indexOf(obj.getCarrera());
                spncarrera.setSelection(position);
            }
        });
        //
        hilo.start();
    }

    void GrabarAlumno(Alumnos obj)
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                AppDB.getINSTANCE(EditarAlumnoActivity.this).alumnosdao().ActualizarAlumno(obj);

                //mandamos un mensaje
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //
                        Toast.makeText(EditarAlumnoActivity.this,
                                "Alumno " + obj.getNombre() + " actualizado correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //
        hilo.start();
    }

    public void EnlazarControles(){
        edtnombre= findViewById(R.id.EDTNOMBALUMNO2);
        edtdni= findViewById(R.id.EDTDNIALUMNO2);
        edttelefono=findViewById(R.id.EDTTELFALUMNO2);
        edtcorreo=findViewById(R.id.EDTCORREOALUMNO2);
        btnedita=findViewById(R.id.BTNEDITARALUMNO2);
        btncancelar=findViewById(R.id.BTNCANCELARALUMNO2);
        spncarrera=findViewById(R.id.SPNCARR2);
    }
}