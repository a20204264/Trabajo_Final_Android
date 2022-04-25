package com.example.trabajo_final.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabajo_final.Entidades.Cursos;
import com.example.trabajo_final.Entidades.Notas;
import com.example.trabajo_final.Entidades.QueryNotas;

import java.util.List;

@Dao
public interface NotasDAO {

    @Insert
    void InsertarNota(Notas obj);


    @Query("SELECT n.codigo_notas as codigo_notas, n.nota1 as nota1,n.nota2 as nota2,n.nota3 as nota3,n.nota4 as nota4,c.curso as curso,a.nombre as alumno FROM notas n, cursos c, alumnos a WHERE c.codigo_curso = n.codigo_curso AND n.codigo_alumno = a.codigo_alumno AND codigo_notas= :codigo")
    QueryNotas TraerNota(int codigo);


    @Query("SELECT n.codigo_notas as codigo_notas, n.nota1 as nota1,n.nota2 as nota2,n.nota3 as nota3,n.nota4 as nota4,c.curso as curso,a.nombre as alumno FROM notas n, cursos c, alumnos a WHERE c.codigo_curso = n.codigo_curso AND n.codigo_alumno = a.codigo_alumno")
    List<QueryNotas> ListarNotasAll();


    @Query("DELETE FROM notas WHERE codigo_notas= :codigo ")
    void EliminarNota(int codigo);


    @Update
    void ActualizarNota(Notas obj);
}
