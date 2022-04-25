package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Carreras;
import com.example.trabajo_final.Entidades.Profesores;

import java.util.ArrayList;
import java.util.List;

public class NuevoCarreraActivity extends AppCompatActivity {

    EditText edtnombre;
    Button btnagregar, btncancelar;
    Carreras obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_carrera);
        EnlazarControles();

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carreras obj=new Carreras(edtnombre.getText().toString());

                GrabarCarrera(obj);
                Intent i=new Intent(NuevoCarreraActivity.this, CarrerasActivity.class);
                startActivity(i);
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(NuevoCarreraActivity.this, CarrerasActivity.class);
                startActivity(i);
            }
        });
    }

    void GrabarCarrera(Carreras obj)
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                AppDB.getINSTANCE(NuevoCarreraActivity.this).carrerasdao().InsertarCarrera(obj);

                //mandamos un mensaje
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //
                        Toast.makeText(NuevoCarreraActivity.this,
                                "Carrera: " + obj.getCarrera() + " Grabado Correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //
        hilo.start();
    }

    void EnlazarControles(){
        edtnombre=findViewById(R.id.EDTNOMCARR);
        btnagregar= findViewById(R.id.BTNAGREGARCARR);
        btncancelar=findViewById(R.id.BTNCANCELARCARR);
    }
}