package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Alumnos;
import com.example.trabajo_final.Entidades.Profesores;

public class EditarProfesorActivity extends AppCompatActivity {

    EditText edtnombre, edtdni, edttelefono, edtcorreo;
    Button btnedita, btncancelar;
    Profesores obj;
    int codigoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_profesor);
        codigoSeleccionado = getIntent().getIntExtra("codigo",0);
        EnlazarControles();
        CargarAlumno();

        btnedita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj=new Profesores(codigoSeleccionado,
                        edtnombre.getText().toString(),
                        Integer.parseInt(edtdni.getText().toString()),
                        Integer.parseInt(edttelefono.getText().toString()),
                        edtcorreo.getText().toString());
                GrabarAlumno(obj);

                Intent i= new Intent(EditarProfesorActivity.this, ProfesoresActivity.class);
                startActivity(i);
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(EditarProfesorActivity.this, ProfesoresActivity.class);
                startActivity(i);
            }
        });
    }

    void CargarAlumno(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                obj = AppDB.getINSTANCE(EditarProfesorActivity.this).profesoresdao().TraerProfesor(codigoSeleccionado);
                edtnombre.setText(obj.getNombre());
                edtdni.setText(obj.getDni()+"");
                edttelefono.setText(obj.getTelefono()+"");
                edtcorreo.setText(obj.getCorreo());
            }
        });
        //
        hilo.start();
    }

    void GrabarAlumno(Profesores obj)
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                AppDB.getINSTANCE(EditarProfesorActivity.this).profesoresdao().ActualizarProfesor(obj);

                //mandamos un mensaje
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //
                        Toast.makeText(EditarProfesorActivity.this,
                                "Profesor: " + obj.getNombre() + " Actualizado Correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //
        hilo.start();
    }

    public void EnlazarControles(){
        edtnombre= findViewById(R.id.EDTNOMBPROF2);
        edtdni= findViewById(R.id.EDTDNIPROF2);
        edttelefono=findViewById(R.id.EDTTELFPROF2);
        edtcorreo=findViewById(R.id.EDTCORREOPROF2);
        btnedita=findViewById(R.id.BTNEDITARPROF);
        btncancelar=findViewById(R.id.BTNCANCELARPROF2);
    }
}