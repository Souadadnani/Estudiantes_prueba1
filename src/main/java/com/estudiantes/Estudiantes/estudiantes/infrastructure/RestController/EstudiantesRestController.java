package com.estudiantes.Estudiantes.estudiantes.infrastructure.RestController;

import com.estudiantes.Estudiantes.estudiantes.application.EstudiantesUseCases;
import com.estudiantes.Estudiantes.estudiantes.domain.Estudiante;
import com.estudiantes.Estudiantes.estudiantes.infrastructure.EstudiantesRepositoryMySQl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstudiantesRestController {

    private EstudiantesUseCases estudiantesUseCases;

    public EstudiantesRestController(){
        this.estudiantesUseCases = new EstudiantesUseCases(new EstudiantesRepositoryMySQl());
    }

    @GetMapping("/estudiantes")
    public List<Estudiante> getEstudiantes(){
        return this.estudiantesUseCases.getEstudiantes();
    }
}
