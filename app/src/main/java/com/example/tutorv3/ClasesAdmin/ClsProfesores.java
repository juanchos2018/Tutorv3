package com.example.tutorv3.ClasesAdmin;

public class ClsProfesores {


    String idprofe;
    String aellido;
    String correo;
    String telefono;
    String tipo;
    String nombre;
    String user_image;
    String  nombresalon;
    String sede;
    String edadsalon;
    public ClsProfesores(String idprofe, String aellido, String correo, String telefono, String tipo, String nombre, String user_image,String nombresalon,String sede,String edadsalon) {
        this.idprofe = idprofe;
        this.aellido = aellido;
        this.correo = correo;
        this.telefono = telefono;
        this.tipo = tipo;
        this.nombre = nombre;
        this.user_image = user_image;
        this.nombresalon=nombresalon;
        this.sede=sede;
        this.edadsalon=edadsalon;
    }
    public  ClsProfesores(){

    }

    public String getIdprofe() {
        return idprofe;
    }

    public void setIdprofe(String idprofe) {
        this.idprofe = idprofe;
    }

    public String getAellido() {
        return aellido;
    }

    public void setAellido(String aellido) {
        this.aellido = aellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }


    public String getNombresalon() {
        return nombresalon;
    }

    public void setNombresalon(String nombresalon) {
        this.nombresalon = nombresalon;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getEdadsalon() {
        return edadsalon;
    }

    public void setEdadsalon(String edadsalon) {
        this.edadsalon = edadsalon;
    }
}
