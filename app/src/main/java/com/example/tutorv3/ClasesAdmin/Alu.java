package com.example.tutorv3.ClasesAdmin;

public class Alu {

   String id;
   String nombre;
String apellido;
    String telefono;
    public Alu(String id, String nombre,String apellido,String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido=apellido;
        this.telefono=telefono;

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
