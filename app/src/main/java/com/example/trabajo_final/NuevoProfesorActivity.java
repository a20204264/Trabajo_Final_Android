package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Profesores;

public class NuevoProfesorActivity extends AppCompatActivity {

    EditText edtnombre, edtdni, edttelefono, edtcorreo;
    Button btnagregar, btncancelar;
    Profesores obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_profesor);
        EnlazarControles();

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profesores obj=new Profesores(edtnombre.getText().toString(),
                        Integer.parseInt(edtdni.getText().toString()),
                        Integer.parseInt(edttelefono.getText().toString()),
                        edtcorreo.getText().toString());

                GrabarProfesor(obj);

                Intent i=new Intent(NuevoProfesorActivity.this, ProfesoresActivity.class);

                startActivity(i);
            }
        });



        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(NuevoProfesorActivity.this, ProfesoresActivity.class);
                startActivity(i);
            }
        });
    }

    void GrabarProfesor(Profesores obj)
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                AppDB.getINSTANCE(NuevoProfesorActivity.this).profesoresdao().InsertarProfesor(obj);

                //mandamos un mensaje
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //
                        Toast.makeText(NuevoProfesorActivity.this,
                                "Profesor: " + obj.getNombre() + " Grabado Correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //
        hilo.start();
    }

    void EnlazarControles(){

        edtnombre=findViewById(R.id.EDTNOMBPROF);
        edtdni=findViewById(R.id.EDTDNIPROF);
        edttelefono= findViewById(R.id.EDTTELFPROF);
        edtcorreo= findViewById(R.id.EDTCORREOPROF);
        btnagregar= findViewById(R.id.BTNAGREGARPROF);
        btncancelar=findViewById(R.id.BTNCANCELARPROF);
    }
}