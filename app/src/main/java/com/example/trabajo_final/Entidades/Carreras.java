package com.example.trabajo_final.Entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.trabajo_final.Configuracion.Constantes;

@Entity(tableName = Constantes.TABLA_CARRERAS)
public class Carreras {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="codigo_carrera")
    private int codigo_carrera;

    @ColumnInfo(name="carrera")
    private String carrera;

    public Carreras(){}

    public Carreras(String carrera) {
        this.carrera = carrera;
    }

    public Carreras(int codigo_carrera, String carrera) {
        this.codigo_carrera = codigo_carrera;
        this.carrera = carrera;
    }

    public int getCodigo_carrera() {
        return codigo_carrera;
    }

    public void setCodigo_carrera(int codigo_carrera) {
        this.codigo_carrera = codigo_carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
