package com.estudiantes.Estudiantes.estudiantes.application;

import com.estudiantes.Estudiantes.estudiantes.domain.Estudiante;
import com.estudiantes.Estudiantes.estudiantes.domain.EstudiantesRepository;

import java.util.List;

public class EstudiantesUseCases {

    private EstudiantesRepository estudiantesRepository;
    public EstudiantesUseCases(EstudiantesRepository estudiantesRepository){
        this.estudiantesRepository = estudiantesRepository;
    }

    public List<Estudiante> getEstudiantes(){
        return this.estudiantesRepository.getEstudiantes();
    }

    public Estudiante saveEstudiante(Estudiante estudiante){
        return this.estudiantesRepository.saveEstudiante(estudiante);
    }
}
