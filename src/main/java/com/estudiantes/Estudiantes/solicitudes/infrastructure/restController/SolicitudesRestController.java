package com.estudiantes.Estudiantes.solicitudes.infrastructure.restController;

import com.estudiantes.Estudiantes.solicitudes.application.SolicitudesUseCases;
import com.estudiantes.Estudiantes.solicitudes.domain.Documento;
import com.estudiantes.Estudiantes.solicitudes.domain.Estado;
import com.estudiantes.Estudiantes.solicitudes.domain.Solicitud;
import com.estudiantes.Estudiantes.solicitudes.domain.SolicitudesRepository;
import com.estudiantes.Estudiantes.solicitudes.infrastructure.SolicitudesRepositoryMySQL;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudesRestController {

    private SolicitudesUseCases solicitudesUseCases;
    public SolicitudesRestController(){
        this.solicitudesUseCases = new SolicitudesUseCases(new SolicitudesRepositoryMySQL());
    }

    @GetMapping("/")
    List<Solicitud> getSolicitudes(){
        return this.solicitudesUseCases.getSolicitudes();
    }

    @GetMapping("/pendientes")
    List<Solicitud> getSolicitudesPendientes(){
        return this.solicitudesUseCases.getSolicitudesPendientes();
    }

    @GetMapping("/tipos")
    HashMap<String, Integer> getSolicitudesPorTipo(){
        return this.solicitudesUseCases.getSolicitudesPorTipo();
    }

    @GetMapping("/{id}")
    Solicitud find(@PathVariable Integer id){
        return this.solicitudesUseCases.find(id);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    Solicitud saveSolicitud(@RequestBody Solicitud solicitud){
        return this.solicitudesUseCases.saveSolicitud(solicitud);
    }

    @PutMapping(path = "/{idSolicitud}/documentacion", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Documento> saveDocumento(@RequestBody Documento documento, @PathVariable Integer idSolicitud){
        return this.solicitudesUseCases.saveDocumento(documento, idSolicitud);
    }

    @PutMapping(path = "/{idSolicitud}/estado", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Estado> saveEstado(@RequestBody Estado estado, @PathVariable Integer idSolicitud){
        return this.solicitudesUseCases.saveEstado(estado, idSolicitud);
    }
//hay hacer un requestBody a cada clase o solo uno
    @PostMapping(path = "/completa", consumes = MediaType.APPLICATION_JSON_VALUE)
    Solicitud saveSolicitudCompleta(@RequestBody Solicitud solicitud, Estado estado, Documento documento){
        return this.solicitudesUseCases.saveSolicitudCompleta(solicitud, estado, documento);
    }

}
