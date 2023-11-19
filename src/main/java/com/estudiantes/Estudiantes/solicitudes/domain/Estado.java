package com.estudiantes.Estudiantes.solicitudes.domain;

public class Estado {

    private String actualizacion;
    private String comentarios;

    public Estado(String actualizacion, String comentarios) {
        this.actualizacion = actualizacion;
        this.comentarios = comentarios;
    }

    public String getActualizacion() {
        return actualizacion;
    }

    public String getComentarios() {
        return comentarios;
    }
}
