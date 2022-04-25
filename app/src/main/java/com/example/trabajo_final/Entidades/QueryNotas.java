package com.example.trabajo_final.Entidades;

import androidx.room.ColumnInfo;

public class QueryNotas {
    private int codigo_notas;
    private int nota1;
    private int nota2;
    private int nota3;
    private int nota4;
    private String curso;
    private String alumno;

    public QueryNotas(){}

    public QueryNotas(int codigo_notas, int nota1, int nota2, int nota3, int nota4, String curso, String alumno) {
        this.codigo_notas = codigo_notas;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.curso = curso;
        this.alumno = alumno;
    }

    public int getCodigo_notas() {
        return codigo_notas;
    }

    public void setCodigo_notas(int codigo_notas) {
        this.codigo_notas = codigo_notas;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

    public int getNota4() {
        return nota4;
    }

    public void setNota4(int nota4) {
        this.nota4 = nota4;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }
}
