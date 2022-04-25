package com.example.trabajo_final.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabajo_final.Entidades.Alumnos;
import com.example.trabajo_final.Entidades.Profesores;

import java.util.List;

@Dao
public interface ProfesoresDAO {


    @Insert
    void InsertarProfesor(Profesores obj);


    @Query("SELECT * FROM profesores WHERE codigo_profesor= :codigo")
    Profesores TraerProfesor(int codigo);


    @Query("SELECT * FROM profesores")
    List<Profesores> ListarProfesoresAll();


    @Query("DELETE FROM profesores WHERE codigo_profesor= :codigo ")
    void EliminarProfesor(int codigo);


    @Update
    void ActualizarProfesor(Profesores obj);
}