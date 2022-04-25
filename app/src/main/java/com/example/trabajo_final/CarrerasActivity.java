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
import com.example.trabajo_final.Entidades.Carreras;
import com.example.trabajo_final.Entidades.Profesores;

import java.util.ArrayList;
import java.util.List;

public class CarrerasActivity extends AppCompatActivity {
    ListView lv;
    Button btnnuevo, btneditar, btneliminar, btnretro;
    int indice, codigoSeleccionado;
    String nombreSeleccionado;
    Profesores obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreras);
        EnlazarControles();
        TraerCarreras();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = ((TextView)view).getText()+"";
                codigoSeleccionado = Integer.parseInt(str.substring(8,str.indexOf("\n")));
                nombreSeleccionado = str.substring(str.indexOf("Nombre: ")+8,str.length()-1);
            }
        });

        btnnuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(CarrerasActivity.this,NuevoCarreraActivity.class);
                startActivity(i);
            }
        });

        btnretro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(CarrerasActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CarrerasActivity.this, EditarCarreraActivity.class);
                i.putExtra("codigo",codigoSeleccionado);
                startActivity(i);
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarCarreras();
            }
        });
    }

    void TraerCarreras()
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Carreras> lista =
                        AppDB.getINSTANCE(CarrerasActivity.this).carrerasdao().ListarCarrerasAll();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MostrarCarrerasListView(lista);
                    }
                });
            }
        });
        //
        hilo.start();
    }

    void EliminarCarreras(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDB.getINSTANCE(CarrerasActivity.this).carrerasdao().EliminarCarrera(codigoSeleccionado);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TraerCarreras();
                        Toast.makeText(CarrerasActivity.this,"Carrera "+nombreSeleccionado+" eliminado exitosamente",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        hilo.start();
    }

    void MostrarCarrerasListView(List<Carreras> xlista)
    {
        List<String> mi_lista = new ArrayList<>();

        String cad= "";
        for(Carreras carrera: xlista)
        {
            cad= "Codigo: " + carrera.getCodigo_carrera() + "\n" +
                    "Nombre: " + carrera.getCarrera();

            mi_lista.add(cad);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                CarrerasActivity.this,
                android.R.layout.simple_list_item_1,
                mi_lista
        );
        lv.setAdapter(adapter);

        /*Toast.makeText(this,
                "Cantidad de Profesores: " + mi_lista.size(),
                Toast.LENGTH_SHORT).show();*/
    }

    void EnlazarControles(){
        lv =findViewById(R.id.LVCARRERAS);
        btnnuevo= findViewById(R.id.BTNUEVOCARRERA);
        btneditar=findViewById(R.id.BTNEDITARCARRERA);
        btneliminar=findViewById(R.id.BTNELIMINARCARRERA);
        btnretro = findViewById(R.id.BTNRETROCERCARR);
    }
}