package com.example.tutorv3.ClasesAdmin;

public class ClsArchivos {

    String id;
    String ruta;
    String nombreclase;
    String fecha;
    String tipo;
    String idarchivo;
    String nombrearchivo;


    public  ClsArchivos(){

    }
    public ClsArchivos(String id, String ruta, String nombreclase, String fecha, String tipo, String idarchivo,String nombrearchivo) {
        this.id = id;
        this.ruta = ruta;
        this.nombreclase = nombreclase;
        this.fecha = fecha;
        this.tipo = tipo;
        this.idarchivo = idarchivo;
        this.nombrearchivo=nombrearchivo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombreclase() {
        return nombreclase;
    }

    public void setNombreclase(String nombreclase) {
        this.nombreclase = nombreclase;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdarchivo() {
        return idarchivo;
    }

    public void setIdarchivo(String idarchivo) {
        this.idarchivo = idarchivo;
    }

    public String getNombrearchivo() {
        return nombrearchivo;
    }

    public void setNombrearchivo(String nombrearchivo) {
        this.nombrearchivo = nombrearchivo;
    }
}
