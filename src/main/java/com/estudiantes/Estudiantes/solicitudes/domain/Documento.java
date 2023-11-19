package com.estudiantes.Estudiantes.solicitudes.domain;

public class Documento {
    private Integer id;
    private String ruta;

    public Documento(Integer id, String ruta) {
        this.id = id;
        this.ruta = ruta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }
}
