package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Profesores;

import java.util.ArrayList;
import java.util.List;

public class ProfesoresActivity extends AppCompatActivity {
    ListView lv;
    Button btnnuevo, btneditar, btneliminar, btnretro;
    int indice, codigoSeleccionado;
    String nombreSeleccionado;
    Profesores obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);
        EnlazarControles();
        TraerProfesores();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = ((TextView)view).getText()+"";
                codigoSeleccionado = Integer.parseInt(str.substring(8,str.indexOf("\n")));
                nombreSeleccionado = str.substring(str.indexOf("Nombre: ")+8,str.indexOf("\nD"));
            }
        });

        btnnuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ProfesoresActivity.this,NuevoProfesorActivity.class);
                startActivity(i);
            }
        });

        btnretro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ProfesoresActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfesoresActivity.this, EditarProfesorActivity.class);
                i.putExtra("codigo",codigoSeleccionado);
                startActivity(i);
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarAlumnos();
            }
        });
    }

    void TraerProfesores()
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Profesores> lista =
                        AppDB.getINSTANCE(ProfesoresActivity.this).profesoresdao().ListarProfesoresAll();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MostrarProfesoresListView(lista);
                    }
                });
            }
        });
        //
        hilo.start();
    }

    void EliminarAlumnos(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDB.getINSTANCE(ProfesoresActivity.this).profesoresdao().EliminarProfesor(codigoSeleccionado);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TraerProfesores();
                        Toast.makeText(ProfesoresActivity.this,"Profesor "+nombreSeleccionado+" eliminado exitosamente",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        hilo.start();
    }

    void MostrarProfesoresListView(List<Profesores> xlista)
    {
        List<String> mi_lista = new ArrayList<>();

        String cad= "";
        for(Profesores profesor: xlista)
        {
            cad= "Codigo: " + profesor.getCodigo_profesor() + "\n" +
                    "Nombre: " + profesor.getNombre()+ "\n" +
                    "DNI: " + profesor.getDni()+ "\n" +
                    "Telefono: " + profesor.getTelefono()+ "\n" +
                    "Correo: " + profesor.getCorreo();

            mi_lista.add(cad);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ProfesoresActivity.this,
                android.R.layout.simple_list_item_1,
                mi_lista
        );
        lv.setAdapter(adapter);

        /*Toast.makeText(this,
                "Cantidad de Profesores: " + mi_lista.size(),
                Toast.LENGTH_SHORT).show();*/
    }

    void EnlazarControles(){
        lv =findViewById(R.id.LVPROFESORES);
        btnnuevo= findViewById(R.id.BTNNUEVOPROFESOR);
        btneditar=findViewById(R.id.BTNEDITARPROFESOR);
        btneliminar=findViewById(R.id.BTNELIMINARPROFESOR);
        btnretro = findViewById(R.id.BTNRETROPROF);
    }
}