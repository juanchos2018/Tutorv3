package com.example.tutorv3.ClasesAdmin;

public class ClsProfeAlumno {

    String id;
    String nombrealumno;
    String apellidoalumno;
    String edad;
    String nombreapoderado1;
    String correoapoderado1;
    String nombreapoderado2;
    String correoapoderado2;
    String telefono;
    String tokentareas;
    String rutafoto;
    String tipo;


    public ClsProfeAlumno(String id, String nombrealumno, String apellidoalumno, String edad, String nombreapoderado1, String correoapoderado1, String nombreapoderado2, String correoapoderado2, String tokentareas,String telefono,String rutafoto,String tipo) {
        this.id = id;
        this.nombrealumno = nombrealumno;
        this.apellidoalumno = apellidoalumno;
        this.edad = edad;
        this.nombreapoderado1 = nombreapoderado1;
        this.correoapoderado1 = correoapoderado1;
        this.nombreapoderado2 = nombreapoderado2;
        this.correoapoderado2 = correoapoderado2;
        this.tokentareas = tokentareas;
        this.telefono=telefono;
        this.rutafoto=rutafoto;
        this.tipo=tipo;
    }

    public ClsProfeAlumno(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrealumno() {
        return nombrealumno;
    }

    public void setNombrealumno(String nombrealumno) {
        this.nombrealumno = nombrealumno;
    }

    public String getApellidoalumno() {
        return apellidoalumno;
    }

    public void setApellidoalumno(String apellidoalumno) {
        this.apellidoalumno = apellidoalumno;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getNombreapoderado1() {
        return nombreapoderado1;
    }

    public void setNombreapoderado1(String nombreapoderado1) {
        this.nombreapoderado1 = nombreapoderado1;
    }

    public String getCorreoapoderado1() {
        return correoapoderado1;
    }

    public void setCorreoapoderado1(String correoapoderado1) {
        this.correoapoderado1 = correoapoderado1;
    }

    public String getNombreapoderado2() {
        return nombreapoderado2;
    }

    public void setNombreapoderado2(String nombreapoderado2) {
        this.nombreapoderado2 = nombreapoderado2;
    }

    public String getCorreoapoderado2() {
        return correoapoderado2;
    }

    public void setCorreoapoderado2(String correoapoderado2) {
        this.correoapoderado2 = correoapoderado2;
    }


    public String getTokentareas() {
        return tokentareas;
    }

    public void setTokentareas(String tokentareas) {
        this.tokentareas = tokentareas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRutafoto() {
        return rutafoto;
    }

    public void setRutafoto(String rutafoto) {
        this.rutafoto = rutafoto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
