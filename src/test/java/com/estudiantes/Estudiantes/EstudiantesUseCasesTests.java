package com.estudiantes.Estudiantes;

import com.estudiantes.Estudiantes.estudiantes.application.EstudiantesUseCases;
import com.estudiantes.Estudiantes.estudiantes.domain.Estudiante;
import com.estudiantes.Estudiantes.estudiantes.infrastructure.EstudiantesRepositoryMySQl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstudiantesUseCasesTests {

    private EstudiantesUseCases estudiantesUseCases;

    public EstudiantesUseCasesTests(){
        this.estudiantesUseCases = new EstudiantesUseCases(new EstudiantesRepositoryMySQl());
    }

    @Test
    void getEstudiantes(){

        List<Estudiante> estudiantes = this.estudiantesUseCases.getEstudiantes();
        assertEquals(0, estudiantes.size());
    }
}
