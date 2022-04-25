package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Cursos;

public class EditarCursoActivity extends AppCompatActivity {

    EditText edtcurso;
    Button btneditar,btncancelar;
    int codigoSeleccionado;
    Cursos obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cursos);
        codigoSeleccionado = getIntent().getIntExtra("codigo",0);
        EnlazarControles();
        CargarCurso();

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursos objcurso = new Cursos(codigoSeleccionado,
                        edtcurso.getText().toString());
                GrabarCurso(objcurso);

                Intent i = new Intent(EditarCursoActivity.this, CursosActivity.class);
                startActivity(i);
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditarCursoActivity.this, CursosActivity.class);
                startActivity(i);
            }
        });
    }

    void CargarCurso(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                obj = AppDB.getINSTANCE(EditarCursoActivity.this).cursosdao().TraerCurso(codigoSeleccionado);
                edtcurso.setText(obj.getCurso());
            }
        });
        //
        hilo.start();
    }

    void GrabarCurso(Cursos obj)
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                AppDB.getINSTANCE(EditarCursoActivity.this).cursosdao().ActualizarCurso(obj);

                //mandamos un mensaje
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //
                        Toast.makeText(EditarCursoActivity.this,
                                "Curso " + obj.getCurso() + " actualizado correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //
        hilo.start();
    }

    public void EnlazarControles() {
        edtcurso=findViewById(R.id.EDTCURSO2);
        btneditar=findViewById(R.id.BTNEDITARCURSO2);
        btncancelar=findViewById(R.id.BTNCANCELARCURSO2);
    }
}