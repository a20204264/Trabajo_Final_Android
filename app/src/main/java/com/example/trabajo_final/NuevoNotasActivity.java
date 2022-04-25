package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Alumnos;
import com.example.trabajo_final.Entidades.Carreras;
import com.example.trabajo_final.Entidades.Cursos;
import com.example.trabajo_final.Entidades.Notas;
import com.example.trabajo_final.Entidades.QueryAlumnos;
import com.example.trabajo_final.Entidades.QueryNotas;

import java.util.ArrayList;
import java.util.List;

public class NuevoNotasActivity extends AppCompatActivity {

    EditText edtnota1, edtnota2, edtnota3, edtnota4;
    List<String> cursos = new ArrayList<String>(), alumnos = new ArrayList<String>();
    List<Integer> curcodigo = new ArrayList<Integer>(), alucodigo = new ArrayList<Integer>();
    Button btnagregar, btncancelar;
    Spinner spncurso, spnalumno;
    int position = 0, position2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_notas);
        EnlazarControles();
        CargarSpinners();

        spnalumno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position2 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spncurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notas obj=new Notas(Integer.parseInt(edtnota1.getText().toString()),
                        Integer.parseInt(edtnota2.getText().toString()),
                        Integer.parseInt(edtnota3.getText().toString()),
                        Integer.parseInt(edtnota4.getText().toString()),
                        curcodigo.get(position),
                        alucodigo.get(position2));

                GrabarNota(obj);

                Intent i= new Intent(NuevoNotasActivity.this, NotasActivity.class);

                startActivity(i);
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(NuevoNotasActivity.this, NotasActivity.class);

                startActivity(i);
            }
        });
    }

    void GrabarNota(Notas obj)
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                AppDB.getINSTANCE(NuevoNotasActivity.this).notasdao().InsertarNota(obj);

                //mandamos un mensaje
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //
                        Toast.makeText(NuevoNotasActivity.this,
                                "Registro de nota grabado correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //
        hilo.start();
    }

    void CargarSpinners(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                List<Cursos> lista = AppDB.getINSTANCE(NuevoNotasActivity.this).cursosdao().ListarCursosAll();
                for (Cursos c : lista){
                    curcodigo.add(c.getCodigo_curso());
                    cursos.add(c.getCurso());
                }
                spncurso.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,cursos));


                //acceder a la clase AppDB
                List<QueryAlumnos> lista2 = AppDB.getINSTANCE(NuevoNotasActivity.this).alumnosdao().ListarAlumnosAll();
                for (QueryAlumnos c : lista2){
                    alucodigo.add(c.getCodigo_alumno());
                    alumnos.add(c.getNombre());
                }
                spnalumno.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,alumnos));
            }
        });
        //
        hilo.start();
    }

    public void EnlazarControles(){
        edtnota1= findViewById(R.id.EDTNOTA1);
        edtnota2= findViewById(R.id.EDTNOTA2);
        edtnota3=findViewById(R.id.EDTNOTA3);
        edtnota4=findViewById(R.id.NOTAFINAL);
        spncurso=findViewById(R.id.SPNOTACURR);
        spnalumno=findViewById(R.id.SPNALUMNO);
        btnagregar=findViewById(R.id.BTNAGREGARNOTA);
        btncancelar=findViewById(R.id.BTNCANCELARNOTA);
    }
}