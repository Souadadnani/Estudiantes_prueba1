package com.estudiantes.Estudiantes.solicitudes.application;

import com.estudiantes.Estudiantes.solicitudes.domain.Solicitud;
import com.estudiantes.Estudiantes.solicitudes.domain.SolicitudesPorTipoRepository;
import com.estudiantes.Estudiantes.solicitudes.domain.SolicitudesRepository;

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

    public List<Solicitud> getSolicitudesPpendientes(){
        return this.solicitudesRepository.getSolicitudesPendientes();
    }

    public HashMap<String, Integer> getSolicitudesPorTipo(){
        return this.solicitudesPorTipoRepository.getSolicitudesPorTipo();
    }

    public List<Solicitud> find(Integer id){
        return this.solicitudesRepository.find(id);
    }

    public Solicitud saveSolicitud(Solicitud solicitud){
        return  this.solicitudesRepository.saveSolicitud(solicitud);
    }


}
