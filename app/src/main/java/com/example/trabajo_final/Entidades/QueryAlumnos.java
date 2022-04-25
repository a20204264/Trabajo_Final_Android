package com.example.trabajo_final.Entidades;

import androidx.room.ColumnInfo;

public class QueryAlumnos {
    private int codigo_alumno;
    private String nombre;
    private int dni;
    private int telefono;
    private String correo;
    private String carrera;

    public QueryAlumnos(){}

    public QueryAlumnos(int codigo_alumno, String nombre, int dni, int telefono, String correo, String carrera) {
        this.codigo_alumno = codigo_alumno;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.carrera = carrera;
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
