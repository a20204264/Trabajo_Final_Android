package com.example.trabajo_final.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabajo_final.Entidades.Cursos;

import java.util.List;

@Dao
public interface CursosDAO {

    @Insert
    void InsertarCurso(Cursos obj);


    @Query("SELECT * FROM cursos WHERE codigo_curso= :codigo")
    Cursos TraerCurso(int codigo);


    @Query("SELECT * FROM cursos")
    List<Cursos> ListarCursosAll();


    @Query("DELETE FROM cursos WHERE codigo_curso= :codigo ")
    void EliminarCurso(int codigo);


    @Update
    void ActualizarCurso(Cursos obj);
}
