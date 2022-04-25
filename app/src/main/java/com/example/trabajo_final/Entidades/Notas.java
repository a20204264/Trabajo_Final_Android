package com.example.trabajo_final.Entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.trabajo_final.Configuracion.Constantes;

@Entity(tableName = Constantes.TABLA_NOTAS)
public class Notas {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="codigo_notas")
    private int codigo_notas;

    @ColumnInfo(name="nota1")
    private int nota1;

    @ColumnInfo(name="nota2")
    private int nota2;

    @ColumnInfo(name="nota3")
    private int nota3;

    @ColumnInfo(name="nota4")
    private int nota4;

    @ColumnInfo(name="codigo_curso")
    private int codigo_curso;

    @ColumnInfo(name="codigo_alumno")
    private int codigo_alumno;

    public Notas(){}

    public Notas(int nota1, int nota2, int nota3, int nota4, int codigo_curso, int codigo_alumno) {
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.codigo_curso = codigo_curso;
        this.codigo_alumno = codigo_alumno;
    }

    public Notas(int codigo_notas, int nota1, int nota2, int nota3, int nota4, int codigo_curso, int codigo_alumno) {
        this.codigo_notas = codigo_notas;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.codigo_curso = codigo_curso;
        this.codigo_alumno = codigo_alumno;
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

    public int getCodigo_curso() {
        return codigo_curso;
    }

    public void setCodigo_curso(int codigo_curso) {
        this.codigo_curso = codigo_curso;
    }

    public int getCodigo_alumno() {
        return codigo_alumno;
    }

    public void setCodigo_alumno(int codigo_alumno) {
        this.codigo_alumno = codigo_alumno;
    }
}
