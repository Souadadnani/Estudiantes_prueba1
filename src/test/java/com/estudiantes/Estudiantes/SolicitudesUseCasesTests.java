package com.estudiantes.Estudiantes;

import com.estudiantes.Estudiantes.estudiantes.application.EstudiantesUseCases;
import com.estudiantes.Estudiantes.estudiantes.domain.Estudiante;
import com.estudiantes.Estudiantes.estudiantes.infrastructure.EstudiantesRepositoryMySQl;
import com.estudiantes.Estudiantes.solicitudes.application.SolicitudesUseCases;
import com.estudiantes.Estudiantes.solicitudes.domain.Documento;
import com.estudiantes.Estudiantes.solicitudes.domain.Solicitud;
import com.estudiantes.Estudiantes.solicitudes.infrastructure.SolicitudesRepositoryMySQL;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolicitudesUseCasesTests {

    private SolicitudesUseCases solicitudesUseCases;
    private EstudiantesUseCases estudiantesUseCases;
    public SolicitudesUseCasesTests(){
        this.solicitudesUseCases = new SolicitudesUseCases(new SolicitudesRepositoryMySQL());
        this.estudiantesUseCases = new EstudiantesUseCases(new EstudiantesRepositoryMySQl());
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
        Estudiante estudiante = this.estudiantesUseCases.saveEstudiante(new Estudiante(null));
        Solicitud solicitud = new Solicitud(null, "convalidacion", estudiante.getId());
        Solicitud guardada = this.solicitudesUseCases.saveSolicitud(solicitud);
        Solicitud buscada = this.solicitudesUseCases.find(guardada.getId());
        assertEquals(buscada.getId(), guardada.getId());
        assertEquals("convalidacion", buscada.getTipo());
    }
    @Test
    void saveSolicitud(){
        Estudiante estudiante = this.estudiantesUseCases.saveEstudiante(new Estudiante(null));
        Solicitud solicitud = new Solicitud(null, "renuncia convocatoria", estudiante.getId());
        Solicitud guardada = this.solicitudesUseCases.saveSolicitud(solicitud);
        Solicitud buscada = this.solicitudesUseCases.find(guardada.getId());
        assertEquals(buscada.getId(), guardada.getId());
    }
    @Test
    void saveDocumento(){
        Estudiante estudiante = this.estudiantesUseCases.saveEstudiante(new Estudiante(null));
        Solicitud guardada = this.solicitudesUseCases.saveSolicitud( new Solicitud(null, "renuncia convocatoria", estudiante.getId()));
        List<Documento> documentos = this.solicitudesUseCases.saveDocumento(new Documento(null, "convalidacion-solicitud1.pdf"), guardada.getId());

    }

}
