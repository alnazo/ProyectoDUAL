package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {
    private Connection connection;

    public ServicioDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Servicio> obtenerTodoServicio() {
        List<Servicio> servicios = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM servicio");
            while (resultSet.next()) {
                Servicio servicio = new Servicio(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getFloat("precio"),
                        resultSet.getString("web")
                );
                servicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicios;
    }

    public Servicio obtenerServicio(int id) {
        Servicio servicio = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM servicio WHERE id = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                servicio = new Servicio(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getFloat("precio"),
                        resultSet.getString("web")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicio;
    }


    public void insertarServicio(Servicio servicio) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO servicio (nombre, precio, web) VALUES (?, ?, ?)"
            );
            statement.setString(1, servicio.getNombre());
            statement.setDouble(2, servicio.getPrecio());
            statement.setString(3, servicio.getWeb());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void actualizarServicio(Servicio servicio) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE servicio SET nombre = ?, precio = ?, web = ? WHERE id = ?"
            );
            statement.setString(1, servicio.getNombre());
            statement.setDouble(2, servicio.getPrecio());
            statement.setString(3, servicio.getWeb());
            statement.setInt(4, servicio.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarServicio(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM usuario WHERE id = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            }

        }

    }