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
import android.widget.Toast;

import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Alumnos;
import com.example.trabajo_final.Entidades.Cursos;
import com.example.trabajo_final.Entidades.Notas;
import com.example.trabajo_final.Entidades.QueryAlumnos;
import com.example.trabajo_final.Entidades.QueryNotas;

import java.util.ArrayList;
import java.util.List;

public class EditarNotasActivity extends AppCompatActivity {

    EditText edtnota1, edtnota2, edtnota3, edtnota4;
    List<String> cursos = new ArrayList<String>(), alumnos = new ArrayList<String>();
    List<Integer> curcodigo = new ArrayList<Integer>(), alucodigo = new ArrayList<Integer>();
    Spinner spncurso, spnalumno;
    int position = 0, position2 = 0;
    Button btnedita, btncancelar;
    QueryNotas obj;
    int codigoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_notas);
        codigoSeleccionado = getIntent().getIntExtra("codigo",0);
        EnlazarControles();
        CargarSpinners();
        CargarNota();

        spncurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnalumno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position2 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnedita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notas obj=new Notas(codigoSeleccionado,
                        Integer.parseInt(edtnota1.getText().toString()),
                        Integer.parseInt(edtnota2.getText().toString()),
                        Integer.parseInt(edtnota3.getText().toString()),
                        Integer.parseInt(edtnota4.getText().toString()),
                        curcodigo.get(position),
                        alucodigo.get(position2));

                GrabarNota(obj);

                Intent i= new Intent(EditarNotasActivity.this, NotasActivity.class);

                startActivity(i);
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(EditarNotasActivity.this, NotasActivity.class);

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
                AppDB.getINSTANCE(EditarNotasActivity.this).notasdao().ActualizarNota(obj);

                //mandamos un mensaje
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //
                        Toast.makeText(EditarNotasActivity.this,
                                "Nota actualizada correctamente",
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
                List<Cursos> lista = AppDB.getINSTANCE(EditarNotasActivity.this).cursosdao().ListarCursosAll();
                for (Cursos c : lista){
                    curcodigo.add(c.getCodigo_curso());
                    cursos.add(c.getCurso());
                }
                spncurso.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,cursos));

                //acceder a la clase AppDB
                List<QueryAlumnos> lista2 = AppDB.getINSTANCE(EditarNotasActivity.this).alumnosdao().ListarAlumnosAll();
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

    void CargarNota(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                //acceder a la clase AppDB
                obj = AppDB.getINSTANCE(EditarNotasActivity.this).notasdao().TraerNota(codigoSeleccionado);
                edtnota1.setText(obj.getNota1()+"");
                edtnota2.setText(obj.getNota2()+"");
                edtnota3.setText(obj.getNota3()+"");
                edtnota4.setText(obj.getNota4()+"");
                position = cursos.indexOf(obj.getCurso());
                spncurso.setSelection(position);
                position2 = cursos.indexOf(obj.getAlumno());
                spnalumno.setSelection(position2);
            }
        });
        //
        hilo.start();
    }

    public void EnlazarControles(){
        edtnota1= findViewById(R.id.EDTNOTA12);
        edtnota2= findViewById(R.id.EDTNOTA22);
        edtnota3=findViewById(R.id.EDTNOTA32);
        edtnota4=findViewById(R.id.NOTAFINAL2);
        spncurso=findViewById(R.id.SPNOTACURR2);
        spnalumno=findViewById(R.id.SPNALUMNO2);
        btnedita=findViewById(R.id.BTNEDITARNOTA2);
        btncancelar=findViewById(R.id.BTNCANCELARNOTA2);
    }
}