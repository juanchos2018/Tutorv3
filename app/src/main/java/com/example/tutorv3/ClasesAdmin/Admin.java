package com.example.tutorv3.ClasesAdmin;

public class Admin {

    private String id;
    private String codigo;
    private String clave;
    private String nombre;

    public Admin( String codigo ,String nombre, String clave) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.clave = clave;

    }
    public Admin(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
