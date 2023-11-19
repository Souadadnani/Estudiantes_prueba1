package com.estudiantes.Estudiantes.solicitudes.application;

import com.estudiantes.Estudiantes.solicitudes.domain.*;

import java.util.HashMap;
import java.util.List;

public class SolicitudesUseCases {

    private SolicitudesRepository solicitudesRepository;
    private SolicitudesPorTipoRepository solicitudesPorTipoRepository;
    public  SolicitudesUseCases(SolicitudesRepository solicitudesRepository){
        this.solicitudesRepository = solicitudesRepository;
    }

    public List<Solicitud> getSolicitudes(){
        return this.solicitudesRepository.getSolicitudes();
    }

    public List<Solicitud> getSolicitudesPendientes(){
        return this.solicitudesRepository.getSolicitudesPendientes();
    }

    public HashMap<String, Integer> getSolicitudesPorTipo(){
        return this.solicitudesPorTipoRepository.getSolicitudesPorTipo();
    }

    public Solicitud find(Integer id){
        return this.solicitudesRepository.find(id);
    }

    public Solicitud saveSolicitud(Solicitud solicitud){
        return  this.solicitudesRepository.saveSolicitud(solicitud);
    }

    public List<Documento> saveDocumento(Documento documento, Integer idSolicitud){
        return this.solicitudesRepository.saveDocumento(documento, idSolicitud);
    }

    public List<Estado> saveEstado(Estado estado, Integer idSolicitud){
        return this.solicitudesRepository.saveEstado(estado, idSolicitud);
    }

    public  Solicitud saveSolicitudCompleta(Solicitud solicitud, Estado estado, Documento documento){
        return this.solicitudesRepository.saveSolicitudCompleta(solicitud, estado, documento);
    }
}
