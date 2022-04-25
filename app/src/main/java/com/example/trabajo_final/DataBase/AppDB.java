package com.example.trabajo_final.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trabajo_final.Configuracion.Constantes;
import com.example.trabajo_final.DAO.AlumnosDAO;
import com.example.trabajo_final.DAO.CarrerasDAO;
import com.example.trabajo_final.DAO.CursosDAO;
import com.example.trabajo_final.DAO.NotasDAO;
import com.example.trabajo_final.DAO.ProfesoresDAO;
import com.example.trabajo_final.Entidades.Alumnos;
import com.example.trabajo_final.Entidades.Carreras;
import com.example.trabajo_final.Entidades.Cursos;
import com.example.trabajo_final.Entidades.Notas;
import com.example.trabajo_final.Entidades.Profesores;


@Database(entities = {Cursos.class, Alumnos.class, Profesores.class, Carreras.class, Notas.class},version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB INSTANCE;

    public abstract CursosDAO cursosdao();
    public abstract AlumnosDAO alumnosdao();
    public abstract ProfesoresDAO profesoresdao();
    public abstract CarrerasDAO carrerasdao();
    public abstract NotasDAO notasdao();

    public static AppDB getINSTANCE(Context contexto){

        if(INSTANCE== null){
            INSTANCE= Room.databaseBuilder(
                    contexto.getApplicationContext(),
                    AppDB.class,
                    Constantes.NOM_DB
            )
                    .build();
        }

        return INSTANCE;
    }
}
