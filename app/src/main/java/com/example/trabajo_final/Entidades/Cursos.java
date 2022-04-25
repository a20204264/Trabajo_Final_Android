package com.example.trabajo_final.Entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.trabajo_final.Configuracion.Constantes;

@Entity(tableName = Constantes.NOM_TABLA)
public class Cursos {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="codigo_curso")
    private int codigo_curso;

    @ColumnInfo(name="curso")
    private String curso;

    public Cursos() {
    }

    public Cursos(int codigo, String curso) {
        this.codigo_curso = codigo;
        this.curso = curso;
    }

    public Cursos(String curso) {
        this.curso = curso;
    }

    public int getCodigo_curso() {
        return codigo_curso;
    }

    public void setCodigo_curso(int codigo_curso) {
        this.codigo_curso = codigo_curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
