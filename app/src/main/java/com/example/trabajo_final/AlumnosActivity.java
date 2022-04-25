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
import com.example.trabajo_final.Entidades.Alumnos;
import com.example.trabajo_final.Entidades.QueryAlumnos;

import java.util.ArrayList;
import java.util.List;

public class AlumnosActivity extends AppCompatActivity {
    ListView lv;
    Button btnnuevo, btneliminar, btneditar,btnretro;
    int codigoSeleccionado;
    String nombreSeleccionado;

    Alumnos obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);
        EnlazarControles();
        TraerAlumnos();
        
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
                Intent i = new Intent(AlumnosActivity.this, NuevoAlumnoActivity.class);
                startActivity(i);
            }
        });

        btnretro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlumnosActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AlumnosActivity.this, EditarAlumnoActivity.class);
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

    void TraerAlumnos()
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                List<QueryAlumnos> lista =
                        AppDB.getINSTANCE(AlumnosActivity.this).alumnosdao().ListarAlumnosAll();

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

    void EliminarAlumnos(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDB.getINSTANCE(AlumnosActivity.this).alumnosdao().EliminarAlumno(codigoSeleccionado);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TraerAlumnos();
                        Toast.makeText(AlumnosActivity.this,"Alumno "+nombreSeleccionado+" eliminado exitosamente",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        hilo.start();
    }

    void MostrarAlumnosListView(List<QueryAlumnos> xlista)
    {
        List<String> mi_lista = new ArrayList<>();

        String cad= "";
        for(QueryAlumnos alumno: xlista)
        {
            cad= "Codigo: " + alumno.getCodigo_alumno() + "\n" +
                    "Nombre: " + alumno.getNombre()+ "\n" +
                    "DNI: " + alumno.getDni()+ "\n" +
                    "Telefono: " + alumno.getTelefono()+ "\n" +
                    "Correo: " + alumno.getCorreo()+"\n"+
                    "Carrera: " + alumno.getCarrera();

            mi_lista.add(cad);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AlumnosActivity.this,
                android.R.layout.simple_list_item_1,
                mi_lista
        );
        lv.setAdapter(adapter);

        /*Toast.makeText(this,
                "Cantidad de Alumnos: " + mi_lista.size(),
                Toast.LENGTH_SHORT).show();*/
    }


    void EnlazarControles(){

        btnnuevo= findViewById(R.id.BTNNUEVOALUMNO);
        btneliminar= findViewById(R.id.BTNELIMINARALUMNO);
        btneditar = findViewById(R.id.BTNEDITARALUMNO);
        btnretro = findViewById(R.id.BTNRETROCEDER);
        lv = findViewById(R.id.LVALUMNOS);
    }
}