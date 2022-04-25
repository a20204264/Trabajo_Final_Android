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
import com.example.trabajo_final.Entidades.Notas;
import com.example.trabajo_final.Entidades.QueryAlumnos;
import com.example.trabajo_final.Entidades.QueryNotas;

import java.util.ArrayList;
import java.util.List;

public class NotasActivity extends AppCompatActivity {

    ListView lvnotas;
    Button btnnuevo, btnretroceder, btneliminar, btneditar;
    int codigoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        TraerNotas();
        EnlazarControles();

        lvnotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = ((TextView)view).getText()+"";
                codigoSeleccionado = Integer.parseInt(str.substring(8,str.indexOf("\n")));
            }
        });

        btnnuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NotasActivity.this, NuevoNotasActivity.class);
                startActivity(i);
            }
        });

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NotasActivity.this, EditarNotasActivity.class);
                i.putExtra("codigo",codigoSeleccionado);
                startActivity(i);
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarNotas();
            }
        });

        btnretroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NotasActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    void TraerNotas(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                List<QueryNotas> lista =
                        AppDB.getINSTANCE(NotasActivity.this).notasdao().ListarNotasAll();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MostrarAlumnosListView(lista);
                    }
                });
            }
        });
        //
        hilo.start();
    }

    void MostrarAlumnosListView(List<QueryNotas> xlista)
    {
        List<String> mi_lista = new ArrayList<>();

        String cad= "";
        for(QueryNotas notas: xlista)
        {
            double promedio = (notas.getNota1()+notas.getNota2()+notas.getNota3()+notas.getNota4())/4.0;
            cad= "Codigo: " + notas.getCodigo_notas() + "\n" +
                    "Nota1: " + notas.getNota1() + "\n" +
                    "Nota1: " + notas.getNota2()+ "\n" +
                    "Nota1: " + notas.getNota3()+ "\n" +
                    "Nota1: " + notas.getNota4()+ "\n" +
                    "Promedio: " + promedio+"\n"+
                    "Curso: " + notas.getCurso()+"\n"+
                    "Alumno: "+notas.getAlumno();

            mi_lista.add(cad);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                NotasActivity.this,
                android.R.layout.simple_list_item_1,
                mi_lista
        );
        lvnotas.setAdapter(adapter);
    }

    void EliminarNotas(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDB.getINSTANCE(NotasActivity.this).notasdao().EliminarNota(codigoSeleccionado);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TraerNotas();
                        Toast.makeText(NotasActivity.this,"Registro de nota eliminado exitosamente",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        hilo.start();
    }

    void EnlazarControles() {
        lvnotas = findViewById(R.id.LVNOTAS);
        btnnuevo = findViewById(R.id.BTNNUEVONOTA);
        btnretroceder = findViewById(R.id.BTNRETROCEDERNOTA);
        btneliminar = findViewById(R.id.BTNELIMINARNOTA);
        btneditar = findViewById(R.id.BTNEDITARNOTA);
    }
}