package com.estudiantes.Estudiantes.solicitudes.infrastructure;

import com.estudiantes.Estudiantes.context.BDConnection;
import com.estudiantes.Estudiantes.solicitudes.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SolicitudesRepositoryMySQL implements SolicitudesRepository, SolicitudesPorTipoRepository {
    @Override
    public List<Solicitud> getSolicitudes() {
        List<Solicitud> solicitudes = new ArrayList<>();
        try(Statement st = BDConnection.getInstance().createStatement()){
            ResultSet rs = st.executeQuery("select * from solicitudes;");
            while(rs.next()){
                Integer id = rs.getInt(1);
                String tipo = rs.getString(3);
                Integer idEstudiante = rs.getInt(2);
                solicitudes.add(new Solicitud(id, tipo, idEstudiante));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solicitudes;
    }

    @Override
    public List<Solicitud> getSolicitudesPendientes() {
        List<Solicitud> solicitudes = new ArrayList<>();
        Connection con = BDConnection.getInstance();
        String query = "select * from solicitudes where id not in( select solicitud from estados where upper(comentarios) like '%FINALIZADO%'));";
        try (Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Integer id = rs.getInt(1);
                String tipo = rs.getString(3);
                Integer idEstudiante = rs.getInt(2);
                solicitudes.add(new Solicitud(id, tipo, idEstudiante));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solicitudes;
    }

    @Override
    public HashMap<String, Integer> getSolicitudesPorTipo(){
        HashMap<String, Integer> solicitudes = new HashMap<>();
        Connection con = BDConnection.getInstance();
        String query = "select tipo, count(*) as cantidad from solicitudes group by tipo;";
        try (Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Integer cantidad = rs.getInt("cantidad");
                String tipo = rs.getString("tipo");
                solicitudes.put(tipo, cantidad);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solicitudes;
    }

    @Override
    public Solicitud find(Integer id){
        Connection con = BDConnection.getInstance();
        Solicitud solicitud = null;
        String query = "select s.*, e.actualizacion, e.comentarios, d.id as idDocumento, d.ruta from solicitudes s left join estados e on s.id=e.solicitud left join documentos d on s.id=d.solicitud where s.tipo='convalidacion' and s.id="+id+";";
        try(Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                solicitud = new Solicitud(id, rs.getString("tipo"), rs.getInt("estudiante"));
                List<Estado> estados = new ArrayList<>();
                List<Documento> documentos = new ArrayList<>();
                estados.add( new Estado(rs.getString("actualizacion"), rs.getString("comentarios")));
                documentos.add(new Documento(rs.getInt("idDocumento"), rs.getString("ruta")));
                solicitud.setEstados(estados);
                solicitud.setDocumentos(documentos);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solicitud;
    }

    @Override
    public Solicitud saveSolicitud(Solicitud solicitud) {
        try (PreparedStatement ps = BDConnection.getInstance().prepareStatement("insert into solicitudes(estudiante, tipo) values(?,?);", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, solicitud.getIdEstudiante());
            ps.setString(2, solicitud.getTipo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                solicitud.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solicitud;
    }

    public void delete(String tabla, Integer idSolicitud){
        try (PreparedStatement ps = BDConnection.getInstance().prepareStatement("delete from "+tabla+" where solicitud=?;")){
            ps.setInt(1, idSolicitud);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Documento> getDocumentos(Integer idSolicitud){
        List<Documento> documentos = new ArrayList<>();
        try(Statement st = BDConnection.getInstance().createStatement()){
            ResultSet rs = st.executeQuery("select * from documentos where id="+idSolicitud+";");
            while (rs.next()){
                documentos.add(new Documento(rs.getInt("id"), rs.getString("ruta")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return documentos;
    }
    @Override
    public List<Documento> saveDocumento(Documento documento, Integer idSolicitud){
        this.delete("documentos", idSolicitud);
        String query = "insert into documentos(ruta) values(?) where solicitud=?;";
        try(PreparedStatement ps = BDConnection.getInstance().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, documento.getRuta());
            ps.setInt(2, idSolicitud);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                documento.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.getDocumentos(idSolicitud);
    }

    public List<Estado> getEstados(Integer idSolicitud){
        List<Estado> estados = new ArrayList<>();
        try(Statement st = BDConnection.getInstance().createStatement()){
            ResultSet rs = st.executeQuery("select * from estados where id="+idSolicitud+";");
            while (rs.next()){
                estados.add(new Estado(rs.getString("actualizacion"), rs.getString("comentarios")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estados;
    }
    @Override
    public List<Estado> saveEstado(Estado estado, Integer idSolicitud){
        this.delete("estados", idSolicitud);
        String query = "insert into estados(comentarios) values(?) where solicitud=?;";
        try (PreparedStatement ps = BDConnection.getInstance().prepareStatement(query)){
            ps.setString(1, estado.getComentarios());
            ps.setInt(2, idSolicitud);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.getEstados(idSolicitud);
    }

    @Override
    public Solicitud saveSolicitudCompleta(Solicitud solicitud, Estado estado, Documento documento){
        Solicitud solicitudCompleta = this.saveSolicitud(solicitud);
        solicitudCompleta.setDocumentos(this.saveDocumento(documento, solicitudCompleta.getId()));
        solicitudCompleta.setEstados(this.saveEstado(estado, solicitudCompleta.getId()));

        return solicitudCompleta;
    }
}
