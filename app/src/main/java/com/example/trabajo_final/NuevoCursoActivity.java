package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Carreras;
import com.example.trabajo_final.Entidades.Cursos;

import java.util.ArrayList;
import java.util.List;

public class NuevoCursoActivity extends AppCompatActivity {

    EditText edtcurso;
    Button btnagregar,btncancelar;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_curso);
        EnlazarControles();

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursos objcurso = new Cursos(edtcurso.getText().toString());
                GrabarCurso(objcurso);

                Intent i = new Intent(NuevoCursoActivity.this, CursosActivity.class);
                startActivity(i);
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NuevoCursoActivity.this, CursosActivity.class);
                startActivity(i);
            }
        });
    }

    void GrabarCurso(Cursos obj)
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                AppDB.getINSTANCE(NuevoCursoActivity.this).cursosdao().InsertarCurso(obj);

                //mandamos un mensaje
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //
                        Toast.makeText(NuevoCursoActivity.this,
                                "Curso: " + obj.getCurso() + " Grabado Correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //
        hilo.start();
    }

    public void EnlazarControles() {
        edtcurso=findViewById(R.id.EDTCURSO);
        btnagregar=findViewById(R.id.BTNADDCURSO);
        btncancelar=findViewById(R.id.BTNCANCELARCURSO);
    }
}