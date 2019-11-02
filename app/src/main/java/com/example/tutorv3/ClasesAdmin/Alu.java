package com.example.tutorv3.ClasesAdmin;

public class Alu {

   String id;
   String nombre;
String apellido;
    public Alu(String id, String nombre,String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido=apellido;

    }

    public Alu(){

    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
