package com.example.tutorv3.ClasesAdmin;

public class cursotutor {

    private  String id;
    private String codigotutor;
    private String curso;

    public cursotutor(String id, String curso) {
        this.id=id;
        this.curso = curso;
    }

    public cursotutor() {

    }

    public String getCodigotutor() {
        return codigotutor;
    }

    public void setCodigotutor(String codigotutor) {
        this.codigotutor = codigotutor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
