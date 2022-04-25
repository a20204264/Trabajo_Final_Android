package com.example.trabajo_final.Entidades;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.trabajo_final.Configuracion.Constantes;

@Entity(tableName = Constantes.TABLA_ALUMNOS)
public class Alumnos {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="codigo_alumno")
    private int codigo_alumno;

    @ColumnInfo(name="nombre")
    private String nombre;

    @ColumnInfo(name="dni")
    private int dni;

    @ColumnInfo(name="telefono")
    private int telefono;

    @ColumnInfo(name="correo")
    private String correo;

    @ColumnInfo(name="codigo_carrera")
    private int codigo_carrera;

    public Alumnos() {
    }

    public Alumnos(int codigo, String nombre, int dni, int telefono, String correo, int codigo_carrera) {
        this.codigo_alumno = codigo;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.codigo_carrera = codigo_carrera;
    }

    public Alumnos(String nombre, int dni, int telefono, String correo, int codigo_carrera) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.codigo_carrera = codigo_carrera;
    }

    public int getCodigo_alumno() {
        return codigo_alumno;
    }

    public void setCodigo_alumno(int codigo_alumno) {
        this.codigo_alumno = codigo_alumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCodigo_carrera() {
        return codigo_carrera;
    }

    public void setCodigo_carrera(int codigo_carrera) {
        this.codigo_carrera = codigo_carrera;
    }
}
