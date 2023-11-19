package com.estudiantes.Estudiantes.solicitudes.domain;

import com.estudiantes.Estudiantes.estudiantes.domain.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class Solicitud {

    private Integer id;
    private String tipo;
    private Integer idEstudiante;
    private List<Estado> estados;
    private List<Documento> documentos;

    public Solicitud(Integer id, String tipo, Integer idEstudiante) {
        this.id = id;
        this.tipo = tipo;
        this.idEstudiante = idEstudiante;
        this.estados = new ArrayList<>();
        this.documentos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
}
