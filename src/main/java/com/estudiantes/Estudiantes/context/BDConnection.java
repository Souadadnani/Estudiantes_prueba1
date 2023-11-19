package com.estudiantes.Estudiantes.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {

    private static Connection conexion;

    private BDConnection(){}

    public static Connection getInstance(){
        if(conexion == null){
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://172.18.0.1:3306/convalidaciones", "root", "root");
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return conexion;
    }
}
