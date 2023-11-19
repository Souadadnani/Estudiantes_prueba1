package com.estudiantes.Estudiantes.estudiantes.infrastructure;

import com.estudiantes.Estudiantes.context.BDConnection;
import com.estudiantes.Estudiantes.estudiantes.domain.Estudiante;
import com.estudiantes.Estudiantes.estudiantes.domain.EstudiantesRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstudiantesRepositoryMySQl implements EstudiantesRepository {
    @Override
    public List<Estudiante> getEstudiantes() {

        List<Estudiante> estudiantes = new ArrayList<>();
        try (Statement st = BDConnection.getInstance().createStatement()){
            ResultSet rs = st.executeQuery("select * from estudiantes;");

            while(rs.next()){
                estudiantes.add(new Estudiante(rs.getInt("id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  estudiantes;
    }

    @Override
    public void saveEstudiante(Estudiante estudiante) {

        try(PreparedStatement ps = BDConnection.getInstance().prepareStatement("insert into estudiantes(id) values(?);")) {
            ps.setInt(1, estudiante.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

    }
}
