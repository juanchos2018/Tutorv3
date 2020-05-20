package com.example.tutorv3.ClasesAdmin;

public class perro {
    String nomnbre;
    String correo;
    String fecha;

    public perro(String nomnbre, String correo, String fecha) {
        this.nomnbre = nomnbre;
        this.correo = correo;
        this.fecha = fecha;
    }

    public  perro(){

    }
    public String getNomnbre() {
        return nomnbre;
    }

    public void setNomnbre(String nomnbre) {
        this.nomnbre = nomnbre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
