package com.example.tutorv3.ClasesAdmin;

public class curso {

    String id;
    String curso;
    public curso(){

    }
    public curso(String id, String curso) {
        this.id = id;
        this.curso = curso;
    }



    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
