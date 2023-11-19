package com.estudiantes.Estudiantes.solicitudes.domain;

import java.util.List;

public interface SolicitudesRepository {

    List<Solicitud> getSolicitudes();
    List<Solicitud> getSolicitudesPendientes();
    Solicitud find(Integer id);
    Solicitud saveSolicitud(Solicitud solicitud);
    List<Documento> saveDocumento(Documento documento, Integer idSolicitud);
    List<Estado> saveEstado(Estado estado, Integer idSolicitud);
    Solicitud saveSolicitudCompleta(Solicitud solicitud, Estado estado, Documento documento);
}
