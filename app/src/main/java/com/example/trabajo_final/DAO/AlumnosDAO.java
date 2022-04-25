package com.example.trabajo_final.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabajo_final.Entidades.Alumnos;
import com.example.trabajo_final.Entidades.QueryAlumnos;

import java.util.List;

@Dao
public interface AlumnosDAO {

    @Insert
    void InsertarAlumno(Alumnos obj);


    @Query("SELECT a.codigo_alumno as codigo_alumno, a.nombre as nombre, a.dni as dni, a.telefono as telefono, a.correo as correo, c.carrera as carrera FROM alumnos a, carreras c WHERE a.codigo_carrera = c.codigo_carrera AND codigo_alumno= :codigo")
    QueryAlumnos TraerAlumno(int codigo);


    @Query("SELECT a.codigo_alumno as codigo_alumno, a.nombre as nombre, a.dni as dni, a.telefono as telefono, a.correo as correo, c.carrera as carrera FROM alumnos a, carreras c WHERE a.codigo_carrera = c.codigo_carrera")
    List<QueryAlumnos> ListarAlumnosAll();


    @Query("DELETE FROM alumnos WHERE codigo_alumno= :codigo ")
    void EliminarAlumno(int codigo);


    @Update
    void ActualizarAlumno(Alumnos obj);
}
