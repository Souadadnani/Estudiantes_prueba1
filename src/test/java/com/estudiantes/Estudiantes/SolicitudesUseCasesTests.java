package com.estudiantes.Estudiantes;

import com.estudiantes.Estudiantes.solicitudes.application.SolicitudesUseCases;
import com.estudiantes.Estudiantes.solicitudes.domain.Solicitud;
import com.estudiantes.Estudiantes.solicitudes.infrastructure.SolicitudesRepositoryMySQL;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolicitudesUseCasesTests {

    private SolicitudesUseCases solicitudesUseCases;
    public SolicitudesUseCasesTests(){
        this.solicitudesUseCases = new SolicitudesUseCases(new SolicitudesRepositoryMySQL());
    }

    @Test
    void getSolicitudes(){
        assertEquals(0, this.solicitudesUseCases.getSolicitudes().size());
    }

    @Test
    void getSolicitudesPendientes(){
        List<Solicitud> sPendientes = this.solicitudesUseCases.getSolicitudesPendientes();
        assertEquals(0, sPendientes.size());
    }
    @Test
    void getSolicitudesPorTipo(){
        HashMap<String, Integer> solicitudesPorTipo = this.solicitudesUseCases.getSolicitudesPorTipo();
        assertEquals(0, solicitudesPorTipo.size());
    }
    @Test
    void find(){
        Solicitud solicitud = new Solicitud(null, "convalidacion", 12);
        Solicitud guardada = this.solicitudesUseCases.saveSolicitud(solicitud);
        Solicitud buscada = this.solicitudesUseCases.find(guardada.getId());
        assertEquals(buscada.getId(), guardada.getId());
    }

}
