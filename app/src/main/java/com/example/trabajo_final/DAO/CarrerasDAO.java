package com.example.trabajo_final.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabajo_final.Entidades.Carreras;

import java.util.List;

@Dao
public interface CarrerasDAO {

    @Insert
    void InsertarCarrera(Carreras obj);


    @Query("SELECT * FROM carreras WHERE codigo_carrera= :codigo")
    Carreras TraerCarrera(int codigo);


    @Query("SELECT * FROM carreras")
    List<Carreras> ListarCarrerasAll();


    @Query("DELETE FROM carreras WHERE codigo_carrera= :codigo ")
    void EliminarCarrera(int codigo);


    @Update
    void ActualizarCarrera(Carreras obj);
}
