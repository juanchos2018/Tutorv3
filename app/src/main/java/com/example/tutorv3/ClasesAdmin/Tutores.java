package com.example.tutorv3.ClasesAdmin;

public class Tutores {
    String idtutor;
String nombre;
String celular;
String correo;


    public Tutores(String idtutor,String nombre,String celular,String correo) {
        this.idtutor = idtutor;
        this.nombre=nombre;
        this.celular=celular;
        this.correo=correo;


    }
    public  Tutores (){

    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdtutor() {
        return idtutor;
    }

    public void setIdtutor(String idtutor) {
        this.idtutor = idtutor;
    }
}
