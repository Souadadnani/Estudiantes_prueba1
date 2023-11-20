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
    public Estudiante saveEstudiante(Estudiante estudiante) {

        try(PreparedStatement ps = BDConnection.getInstance().prepareStatement("insert into estudiantes default values;", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    estudiante.setId(rs.getInt(1));
                }else{
                    throw new SQLException("No se generaron claves para el estudiante.");
                }
            }catch (SQLException e){
                throw new RuntimeException("Error al insertar el estudiante: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estudiante;
    }

}
