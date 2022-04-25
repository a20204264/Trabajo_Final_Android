package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Carreras;
import com.example.trabajo_final.Entidades.Profesores;

public class EditarCarreraActivity extends AppCompatActivity {

    EditText edtnombre;
    Button btnedita, btncancelar;
    Carreras obj;
    int codigoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_carrera);
        codigoSeleccionado = getIntent().getIntExtra("codigo",0);
        EnlazarControles();
        CargarCarrera();

        btnedita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj=new Carreras(codigoSeleccionado,
                        edtnombre.getText().toString());

                GrabarCarrera(obj);
                Intent i= new Intent(EditarCarreraActivity.this, CarrerasActivity.class);
                startActivity(i);
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(EditarCarreraActivity.this, CarrerasActivity.class);
                startActivity(i);
            }
        });
    }

    void CargarCarrera(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                obj = AppDB.getINSTANCE(EditarCarreraActivity.this).carrerasdao().TraerCarrera(codigoSeleccionado);
                edtnombre.setText(obj.getCarrera());
            }
        });
        //
        hilo.start();
    }

    void GrabarCarrera(Carreras obj)
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                AppDB.getINSTANCE(EditarCarreraActivity.this).carrerasdao().ActualizarCarrera(obj);

                //mandamos un mensaje
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //
                        Toast.makeText(EditarCarreraActivity.this,
                                "Carrera " + obj.getCarrera() + " actualizado correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //
        hilo.start();
    }

    public void EnlazarControles(){
        edtnombre= findViewById(R.id.EDTNOMCARR2);
        btnedita=findViewById(R.id.BTNEDITARCARR);
        btncancelar=findViewById(R.id.BTNCANCELARCARR2);
    }
}