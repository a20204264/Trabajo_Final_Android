package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btniralumnos, btnirprofesores, btnircursos, btncarreras, btnirnotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Enlazar();

        btniralumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AlumnosActivity.class);
                startActivity(i);
            }
        });

        btnirprofesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfesoresActivity.class);
                startActivity(i);
            }
        });

        btnircursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CursosActivity.class);
                startActivity(i);
            }
        });

        btncarreras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CarrerasActivity.class);
                startActivity(i);
            }
        });

        btnirnotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NotasActivity.class);
                startActivity(i);
            }
        });
    }

    public void Enlazar(){
        btniralumnos=findViewById(R.id.BTNIRALUMNOS);
        btnirprofesores=findViewById(R.id.BTNIRPROFESORES);
        btnircursos=findViewById(R.id.BTNIRCURSOS);
        btncarreras=findViewById(R.id.BTNIRCARRERAS);
        btnirnotas=findViewById(R.id.BTNIRNOTAS);
    }
}