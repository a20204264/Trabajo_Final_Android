package com.example.trabajo_final.Entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.trabajo_final.Configuracion.Constantes;

@Entity(tableName = Constantes.TABLA_PROFESORES)
public class Profesores {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="codigo_profesor")
    private int codigo_profesor;

    @ColumnInfo(name="nombre")
    private String nombre;

    @ColumnInfo(name="dni")
    private int dni;

    @ColumnInfo(name="telefono")
    private int telefono;

    @ColumnInfo(name="correo")
    private String correo;

    public Profesores() {
    }

    public Profesores(int codigo, String nombre, int dni, int telefono, String correo) {
        this.codigo_profesor = codigo;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Profesores(String nombre, int dni, int telefono, String correo) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getCodigo_profesor() {
        return codigo_profesor;
    }

    public void setCodigo_profesor(int codigo_profesor) {
        this.codigo_profesor = codigo_profesor;
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
}
