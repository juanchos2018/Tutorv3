package com.example.tutorv3.ClasesAdmin;

public class Grupos {
    String idgrupo;
    String idalumno;
    String cpodigoalumno;
    String nombrealumno;
    String tipo;
    public Grupos(String idgrupo, String idalumno, String cpodigoalumno, String nombrealumno,String tipo) {
        this.idgrupo = idgrupo;
        this.idalumno = idalumno;
        this.cpodigoalumno = cpodigoalumno;
        this.nombrealumno = nombrealumno;
        this.tipo=tipo;
    }

    public String getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(String idalumno) {
        this.idalumno = idalumno;
    }

    public String getCpodigoalumno() {
        return cpodigoalumno;
    }

    public void setCpodigoalumno(String cpodigoalumno) {
        this.cpodigoalumno = cpodigoalumno;
    }

    public String getNombrealumno() {
        return nombrealumno;
    }

    public void setNombrealumno(String nombrealumno) {
        this.nombrealumno = nombrealumno;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
