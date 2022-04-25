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
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabajo_final.DataBase.AppDB;
import com.example.trabajo_final.Entidades.Cursos;

import java.util.ArrayList;
import java.util.List;

public class CursosActivity extends AppCompatActivity {

    EditText edtcurso, edtcarrera;
    Button btnnuevo,btneliminar,btnretro,btneditar;
    ListView lv;
    int codigoSeleccionado, indice;
    String nombreSeleccionado;
    Cursos obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        EnlazarControles();
        TraerCursos();

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
                Intent i = new Intent(CursosActivity.this, NuevoCursoActivity.class);
                startActivity(i);
            }
        });

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CursosActivity.this, EditarCursoActivity.class);
                i.putExtra("codigo",codigoSeleccionado);
                startActivity(i);
            }
        });

        btnretro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(CursosActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarCursos();
            }
        });
    }

    void TraerCursos()
    {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Cursos> lista =
                        AppDB.getINSTANCE(CursosActivity.this).cursosdao().ListarCursosAll();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MostrarCursosListView(lista);
                    }
                });
            }
        });
        //
        hilo.start();
    }

    void EliminarCursos(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDB.getINSTANCE(CursosActivity.this).cursosdao().EliminarCurso(codigoSeleccionado);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TraerCursos();
                        Toast.makeText(CursosActivity.this,"Curso "+nombreSeleccionado+" eliminado exitosamente",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        hilo.start();
    }

    void MostrarCursosListView(List<Cursos> xlista)
    {
        List<String> mi_lista = new ArrayList<>();

        String cad= "";
        for(Cursos curso: xlista)
        {
            cad= "Codigo: " + curso.getCodigo_curso() + "\n" +
                    "Curso: " + curso.getCurso();

            mi_lista.add(cad);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                CursosActivity.this,
                android.R.layout.simple_list_item_1,
                mi_lista
        );
        lv.setAdapter(adapter);

        /*Toast.makeText(this,
                "Cantidad de Cursos: " + mi_lista.size(),
                Toast.LENGTH_SHORT).show();*/

    }


    public void EnlazarControles(){
        lv= findViewById(R.id.LVCURSOS);
        btnnuevo = findViewById(R.id.BTNUEVOCURSO);
        btneliminar = findViewById(R.id.BTNELIMINARCURSO);
        btnretro = findViewById(R.id.BTNRETROCERCURS);
        btneditar = findViewById(R.id.BTNEDITARCURSO);
    }
}