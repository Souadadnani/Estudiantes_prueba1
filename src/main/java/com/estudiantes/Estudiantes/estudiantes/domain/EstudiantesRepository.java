package com.estudiantes.Estudiantes.estudiantes.domain;

import java.util.List;

public interface EstudiantesRepository {

    List<Estudiante> getEstudiantes();

    Estudiante saveEstudiante(Estudiante estudiante);
}
