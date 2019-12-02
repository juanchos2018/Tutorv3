package com.example.tutorv3.ClasesAdmin;

public class Grupo {

    String idgrupo;
    String nombre;
    String curso;

    public Grupo(String idgrupo, String nombre, String curso) {
        this.idgrupo = idgrupo;
        this.nombre = nombre;
        this.curso = curso;
    }
    public Grupo() {

    }

    public String getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(String idgrupo) {
        this.idgrupo = idgrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
