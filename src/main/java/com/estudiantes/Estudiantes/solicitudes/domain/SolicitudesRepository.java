package com.estudiantes.Estudiantes.solicitudes.domain;

import java.util.List;

public interface SolicitudesRepository {

    List<Solicitud> getSolicitudes();
    List<Solicitud> getSolicitudesPendientes();
    List<Solicitud> find(Integer id);
    Solicitud saveSolicitud(Solicitud solicitud);

}
